// Public Portal Script

let allVerifiedRecords = [];
let activeCategory = 'ALL';
let activeDataLayer = 'ALL';

const categoriesMap = {
    'ALL': 'All Categories',
    'IDENTITY': 'Village Identity',
    'GOVERNMENT': 'Government Info',
    'EDUCATION': 'Education Data',
    'AGRICULTURE': 'Agriculture Research',
    'MARKET': 'Market Contribution',
    'FUNDS': 'Govt Funds & Projects',
    'SCHEMES': 'Government Schemes',
    'JOBS': 'Jobs Aggregator',
    'NEWS': 'Village News',
    'HISTORY': 'Village History',
    'GEOGRAPHY': 'Geographical Research',
    'PLACES': 'Public Places Directory',
    'PEOPLE': 'Public Representatives',
    'ELECTION': 'Voter & Election Data'
};

document.addEventListener('DOMContentLoaded', () => {
    initPublicPortal();
});

function initPublicPortal() {
    renderCategoryPills();
    fetchStats();
    fetchVerifiedRecords();
}

function switchView(viewName) {
    const publicView = document.getElementById('publicView');
    const adminView = document.getElementById('adminView');
    const btnPublic = document.getElementById('btnNavPublic');
    const btnAdmin = document.getElementById('btnNavAdmin');

    if (viewName === 'public') {
        publicView.style.display = 'block';
        adminView.style.display = 'none';
        btnPublic.classList.add('active');
        btnAdmin.classList.remove('active');
        fetchVerifiedRecords();
    } else {
        publicView.style.display = 'none';
        adminView.style.display = 'block';
        btnAdmin.classList.add('active');
        btnPublic.classList.remove('active');
        initAdminConsole();
    }
}

function renderCategoryPills() {
    const container = document.getElementById('categoryPills');
    container.innerHTML = '';

    Object.keys(categoriesMap).forEach(key => {
        const btn = document.createElement('button');
        btn.className = `cat-pill ${key === activeCategory ? 'active' : ''}`;
        btn.textContent = categoriesMap[key];
        btn.onclick = () => setCategoryFilter(key);
        container.appendChild(btn);
    });

    // Add Sources Pill
    const sourcesBtn = document.createElement('button');
    sourcesBtn.className = `cat-pill ${activeCategory === 'SOURCES' ? 'active' : ''}`;
    sourcesBtn.innerHTML = '<i class="fa-solid fa-book-bookmark"></i> Our Data Sources';
    sourcesBtn.onclick = () => setCategoryFilter('SOURCES');
    container.appendChild(sourcesBtn);
}

function setCategoryFilter(catKey) {
    activeCategory = catKey;
    renderCategoryPills();
    filterAndRender();
}

function setLayerFilter(layerKey) {
    activeDataLayer = layerKey;
    document.querySelectorAll('.layer-btn').forEach(btn => btn.classList.remove('active'));
    
    if (layerKey === 'ALL') document.querySelectorAll('.layer-btn')[0].classList.add('active');
    else if (layerKey === 'LAYER_1_OFFICIAL') document.querySelectorAll('.layer-btn')[1].classList.add('active');
    else if (layerKey === 'LAYER_2_PUBLIC') document.querySelectorAll('.layer-btn')[2].classList.add('active');
    else if (layerKey === 'LAYER_3_COMMUNITY') document.querySelectorAll('.layer-btn')[3].classList.add('active');

    filterAndRender();
}

function fetchStats() {
    fetch('/api/v1/public/village/stats')
        .then(res => res.json())
        .then(data => {
            document.getElementById('statLayer1').textContent = data.layer1OfficialCount || 0;
            document.getElementById('statLayer2').textContent = data.layer2PublicCount || 0;
            document.getElementById('statLayer3').textContent = data.layer3CommunityCount || 0;
            document.getElementById('statVerified').textContent = data.totalVerifiedRecords || 0;
        })
        .catch(err => console.error('Error fetching stats:', err));
}

function fetchVerifiedRecords() {
    fetch('/api/v1/public/village/records')
        .then(res => res.json())
        .then(data => {
            allVerifiedRecords = data;
            filterAndRender();
        })
        .catch(err => console.error('Error fetching records:', err));
}

function filterPublicRecords() {
    filterAndRender();
}

function filterAndRender() {
    const grid = document.getElementById('publicRecordsGrid');
    const sourcesSection = document.getElementById('sourcesSection');
    const alertBanner = document.getElementById('marketAlertBanner');
    const query = document.getElementById('publicSearchInput').value.toLowerCase().trim();

    if (activeCategory === 'SOURCES') {
        grid.style.display = 'none';
        alertBanner.style.display = 'none';
        sourcesSection.style.display = 'block';
        fetchAndRenderSources();
        return;
    }

    grid.style.display = 'grid';
    sourcesSection.style.display = 'none';

    // Toggle Alert Banner if MARKET category selected
    if (activeCategory === 'MARKET') {
        alertBanner.style.display = 'flex';
    } else {
        alertBanner.style.display = 'none';
    }

    let filtered = allVerifiedRecords.filter(r => {
        // Category match
        if (activeCategory !== 'ALL' && r.category !== activeCategory) return false;

        // Data Layer match
        if (activeDataLayer !== 'ALL' && r.dataLayer !== activeDataLayer) return false;

        // Text query match
        if (query) {
            const titleMatch = r.title && r.title.toLowerCase().includes(query);
            const dataMatch = r.dataJson && r.dataJson.toLowerCase().includes(query);
            const sourceMatch = r.sourceName && r.sourceName.toLowerCase().includes(query);
            if (!titleMatch && !dataMatch && !sourceMatch) return false;
        }

        return true;
    });

    renderRecordsGrid(filtered);
}

function renderRecordsGrid(records) {
    const grid = document.getElementById('publicRecordsGrid');
    grid.innerHTML = '';

    if (records.length === 0) {
        grid.innerHTML = `
            <div style="grid-column: 1/-1; text-align: center; padding: 3rem; color: var(--text-muted);">
                <i class="fa-solid fa-folder-open" style="font-size: 2.5rem; margin-bottom: 1rem; display: block;"></i>
                <p>No verified records match the selected category/filters.</p>
            </div>
        `;
        return;
    }

    records.forEach(r => {
        const card = document.createElement('div');
        card.className = 'record-card';

        const layerCss = r.dataLayer === 'LAYER_1_OFFICIAL' ? 'l1' : (r.dataLayer === 'LAYER_2_PUBLIC' ? 'l2' : 'l3');
        const layerLabel = r.dataLayer === 'LAYER_1_OFFICIAL' ? 'Layer 1 Official' : (r.dataLayer === 'LAYER_2_PUBLIC' ? 'Layer 2 Public' : 'Layer 3 Community');

        let parsedPayload = {};
        try {
            parsedPayload = JSON.parse(r.dataJson);
        } catch(e) {
            parsedPayload = { details: r.dataJson };
        }

        let kvHtml = '';
        Object.keys(parsedPayload).forEach(key => {
            let val = parsedPayload[key];
            if (Array.isArray(val)) val = val.join(', ');
            else if (typeof val === 'object' && val !== null) val = JSON.stringify(val);
            kvHtml += `<div class="kv-pair"><span class="kv-key">${key}:</span> ${val}</div>`;
        });

        // Funds specific badge formatting
        let financialDetails = '';
        if (r.category === 'FUNDS' && (r.approvedAmount || r.releasedAmount || r.spentAmount)) {
            financialDetails = `
                <div style="margin-top: 0.6rem; padding-top: 0.6rem; border-top: 1px dashed rgba(255,255,255,0.1); font-size: 0.82rem;">
                    <div><strong>Financial Year:</strong> ${r.financialYear || 'N/A'}</div>
                    <div style="color: var(--accent-green);"><strong>Approved:</strong> ₹${r.approvedAmount ? r.approvedAmount.toLocaleString() : 'N/A'}</div>
                    <div style="color: var(--accent-blue);"><strong>Released:</strong> ₹${r.releasedAmount ? r.releasedAmount.toLocaleString() : 'N/A'}</div>
                    <div style="color: var(--accent-purple);"><strong>Spent:</strong> ₹${r.spentAmount ? r.spentAmount.toLocaleString() : 'N/A'}</div>
                </div>
            `;
        }

        // Scheme specific badge formatting
        let schemeBadge = '';
        if (r.schemeAvailability) {
            const schemeColor = r.schemeAvailability.includes('Available') ? 'var(--accent-green)' : 'var(--accent-amber)';
            schemeBadge = `<div style="margin-top: 0.5rem; font-size: 0.8rem; color: ${schemeColor}; font-weight: 600;"><i class="fa-solid fa-tag"></i> ${r.schemeAvailability}</div>`;
        }

        card.innerHTML = `
            <div>
                <div class="card-top">
                    <span class="cat-badge">${r.category}</span>
                    <span class="layer-badge ${layerCss}">${layerLabel}</span>
                </div>
                <h3>${r.title}</h3>
                <div class="payload-preview">
                    ${kvHtml}
                    ${financialDetails}
                    ${schemeBadge}
                </div>
            </div>
            <div>
                <div class="confidence-bar-container">
                    <div class="confidence-header">
                        <span>Source Trust: ${r.trustLevel ? r.trustLevel.replace('LEVEL_', 'L') : 'L4'}</span>
                        <span>Confidence: <strong>${r.confidenceScore}%</strong></span>
                    </div>
                    <div class="confidence-bar">
                        <div class="confidence-fill" style="width: ${r.confidenceScore}%;"></div>
                    </div>
                </div>
                <div class="card-footer">
                    <span>Source: <strong>${r.sourceName || 'Public Web'}</strong></span>
                    <a href="${r.sourceUrl || '#'}" target="_blank" class="source-link">Cite <i class="fa-solid fa-arrow-up-right-from-square"></i></a>
                </div>
            </div>
        `;

        grid.appendChild(card);
    });
}

function fetchAndRenderSources() {
    fetch('/api/v1/public/village/sources')
        .then(res => res.json())
        .then(data => {
            const tbody = document.getElementById('sourcesTableBody');
            tbody.innerHTML = '';
            data.sources.forEach(s => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td><strong>${s.category}</strong></td>
                    <td>${s.title}</td>
                    <td>${s.sourceName}</td>
                    <td><span style="color: var(--accent-gold); font-weight: 600;">${s.trustLevel}</span></td>
                    <td><span style="color: var(--accent-green); font-weight: 600;">${s.confidenceScore}</span></td>
                    <td>${s.dataLayer}</td>
                    <td>${s.lastVerifiedDate}</td>
                    <td><a href="${s.sourceUrl}" target="_blank" class="source-link"><i class="fa-solid fa-link"></i> Open</a></td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(err => console.error('Error fetching sources:', err));
}
