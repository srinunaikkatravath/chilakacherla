// Community Portal & Schemes Ecosystem Script

let activeBazaarFilter = 'ALL';
let activeBloodGroupFilter = 'ALL';
let activeSchemeFilter = 'ALL';

document.addEventListener('DOMContentLoaded', () => {
    initThemePreference();
    initCommunityPortal();
});

// 1. THEME SWITCHER (Light Mode default / Dark Mode toggle)
function initThemePreference() {
    const savedTheme = localStorage.getItem('chilaka_theme');
    if (savedTheme === 'dark') {
        document.body.classList.add('dark-theme');
        updateThemeBtnUI(true);
    } else {
        document.body.classList.remove('dark-theme');
        updateThemeBtnUI(false);
    }
}

function toggleTheme() {
    const isDark = document.body.classList.toggle('dark-theme');
    localStorage.setItem('chilaka_theme', isDark ? 'dark' : 'light');
    updateThemeBtnUI(isDark);
}

function updateThemeBtnUI(isDark) {
    const icon = document.getElementById('themeIcon');
    const text = document.getElementById('themeText');
    if (isDark) {
        if (icon) icon.className = 'fa-solid fa-sun';
        if (text) text.innerText = 'Light Mode';
    } else {
        if (icon) icon.className = 'fa-solid fa-moon';
        if (text) text.innerText = 'Dark Mode';
    }
}

function initCommunityPortal() {
    loadNotices();
    loadRecentGrievances();
    loadRbkStocks();
    loadMarketplace();
    loadCraftsmen();
    loadBloodDonors();
    loadJobs();
    loadSchemes();
    loadPanchayatFunds();
    loadVoterRecords();
    loadWardMembers();
    loadVoterMembers();
    loadJobHolders();
    loadEducatedYouth();
}

function switchCommunityTab(tabId) {
    const tabs = ['home', 'schemes', 'sachivalayam', 'farmers', 'bazaar', 'health', 'education', 'voter', 'research'];
    tabs.forEach(t => {
        const sec = document.getElementById(`tab${t.charAt(0).toUpperCase() + t.slice(1)}`);
        if (sec) sec.style.display = (t === tabId) ? 'block' : 'none';
    });

    document.querySelectorAll('.nav-tab').forEach(btn => btn.classList.remove('active'));
    if (event && event.currentTarget && event.currentTarget.classList) {
        event.currentTarget.classList.add('active');
    }

    if (tabId === 'schemes') { loadSchemes(); loadPanchayatFunds(); }
    else if (tabId === 'sachivalayam') loadRecentGrievances();
    else if (tabId === 'farmers') loadRbkStocks();
    else if (tabId === 'bazaar') { loadMarketplace(); loadCraftsmen(); }
    else if (tabId === 'health') loadBloodDonors();
    else if (tabId === 'education') { loadJobs(); loadJobHolders(); loadEducatedYouth(); }
    else if (tabId === 'voter') { loadVoterRecords(); loadWardMembers(); loadVoterMembers(); }
    else if (tabId === 'research') {
        const container = document.getElementById('adminContainer');
        const adminView = document.getElementById('adminView');
        if (container && adminView) {
            container.appendChild(adminView);
            adminView.style.display = 'block';
            if (typeof initAdminConsole === 'function') initAdminConsole();
        }
    }
}

// 2. SCHEMES & FUNDS ECOSYSTEM
function loadSchemes() {
    fetch(`/api/v1/community/schemes?category=${activeSchemeFilter}`)
        .then(res => res.json())
        .then(schemes => {
            const grid = document.getElementById('schemesGridContainer');
            if (!grid) return;
            grid.innerHTML = '';

            schemes.forEach(s => {
                const card = document.createElement('div');
                card.className = 'scheme-card';
                
                const docsEscaped = s.requiredDocuments ? s.requiredDocuments.replace(/'/g, "\\'") : '';
                const schemeEscaped = s.schemeName ? s.schemeName.replace(/'/g, "\\'") : '';

                card.innerHTML = `
                    <div>
                        <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:0.6rem;">
                            <span class="scheme-badge ${s.status}">${s.status.replace('_',' ')}</span>
                            <span style="font-size:0.78rem; color:var(--text-muted);"><i class="fa-solid fa-clock"></i> Deadline: <strong>${s.applicationDeadline}</strong></span>
                        </div>
                        <h3 style="font-size:1.1rem; color:var(--text-bright); font-family:var(--font-heading); margin-bottom:0.4rem;">${s.schemeName}</h3>
                        <div class="scheme-benefit-box">
                            <span style="font-size:0.75rem; color:var(--text-muted); display:block;">Financial Benefit:</span>
                            <strong>${s.financialBenefit}</strong>
                        </div>
                        <p style="font-size:0.84rem; color:var(--text-main); margin-bottom:0.6rem;"><strong>Eligibility:</strong> ${s.eligibilityCriteria}</p>
                    </div>

                    <div>
                        <div style="font-size:0.78rem; color:var(--text-muted); margin-bottom:0.8rem; display:flex; justify-content:space-between;">
                            <span>Sanctioned in Village: <strong style="color:var(--accent-cyan);">${s.beneficiaryCount} Families</strong></span>
                            <span>Level: <strong>${s.governmentLevel.replace(/_/g,' ')}</strong></span>
                        </div>
                        <div style="display:flex; gap:0.5rem;">
                            <button class="btn btn-sm btn-primary flex-1" style="justify-content:center;" onclick="openApplySchemeModal('${schemeEscaped}', '${docsEscaped}')">
                                <i class="fa-solid fa-paper-plane"></i> Apply Online
                            </button>
                            ${s.applyUrl ? `<a href="${s.applyUrl}" target="_blank" class="btn btn-sm btn-accent"><i class="fa-solid fa-arrow-up-right-from-square"></i> Portal</a>` : ''}
                        </div>
                    </div>
                `;
                grid.appendChild(card);
            });
        });
}

function filterSchemes(catKey) {
    activeSchemeFilter = catKey;
    document.querySelectorAll('#tabSchemes .bazaar-btn').forEach(btn => btn.classList.remove('active'));
    if (event && event.currentTarget) event.currentTarget.classList.add('active');
    loadSchemes();
}

function openApplySchemeModal(schemeName, docChecklist) {
    document.getElementById('modalSchemeName').value = schemeName;
    document.getElementById('displaySchemeName').value = schemeName;
    document.getElementById('modalDocChecklist').innerText = docChecklist || 'Standard Aadhaar, Rice Card, and Bank Passbook required.';
    document.getElementById('applySchemeModal').style.display = 'flex';
}

function closeApplySchemeModal() {
    document.getElementById('applySchemeModal').style.display = 'none';
}

function handleSchemeApplySubmit(event) {
    event.preventDefault();
    const schemeName = document.getElementById('modalSchemeName').value;
    const applicantName = document.getElementById('applicantName').value;
    const applicantAadhar = document.getElementById('applicantAadhar').value;
    const phone = document.getElementById('applicantPhone').value;
    const rationCardNo = document.getElementById('applicantRation').value;

    fetch('/api/v1/community/schemes/apply', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ schemeName, applicantName, applicantAadhar, phone, rationCardNo })
    })
    .then(res => res.json())
    .then(data => {
        closeApplySchemeModal();
        alert(`Application Submitted Successfully!\nYour Tracking Application ID is: ${data.applicationId}\nPlease present your original documents at Gram Sachivalayam for e-KYC verification.`);
    });
}

function trackSchemeApplication() {
    const appId = document.getElementById('schemeTrackInput').value.trim();
    const displayBox = document.getElementById('schemeTrackResult');
    if (!appId) {
        alert('Please enter a Scheme Application ID.');
        return;
    }

    fetch(`/api/v1/community/schemes/track/${appId}`)
        .then(res => {
            if (!res.ok) throw new Error('Not found');
            return res.json();
        })
        .then(app => {
            displayBox.style.display = 'block';
            displayBox.innerHTML = `
                <div style="display:flex; justify-content:space-between; align-items:center;">
                    <div>
                        <strong>Application ID: ${app.applicationId}</strong> (${app.schemeName})
                        <div style="font-size:0.85rem; color:var(--text-muted); margin-top:0.2rem;">
                            Applicant: <strong>${app.applicantName}</strong> | Phone: ${app.phone} | Ration Card: ${app.rationCardNo}
                        </div>
                    </div>
                    <span class="tag-badge glow">${app.status}</span>
                </div>
            `;
        })
        .catch(() => {
            displayBox.style.display = 'block';
            displayBox.innerHTML = `<span style="color:var(--accent-red);"><i class="fa-solid fa-circle-xmark"></i> Scheme Application ID '${appId}' not found.</span>`;
        });
}

function loadPanchayatFunds() {
    fetch('/api/v1/community/funds')
        .then(res => res.json())
        .then(funds => {
            const grid = document.getElementById('fundsGridContainer');
            if (!grid) return;
            grid.innerHTML = '';

            funds.forEach(f => {
                const utilPct = f.utilizationPercentage || Math.round((f.spentAmount / f.allocatedAmount) * 100);
                const card = document.createElement('div');
                card.className = 'fund-card';
                card.innerHTML = `
                    <div style="display:flex; justify-content:space-between; align-items:center; margin-bottom:0.6rem;">
                        <span style="font-size:0.78rem; font-weight:700; color:var(--accent-blue); uppercase">FY ${f.financialYear}</span>
                        <span class="tag-badge glow">${f.status}</span>
                    </div>
                    <h4 style="font-size:1.1rem; color:var(--text-bright); font-family:var(--font-heading); margin-bottom:0.4rem;">${f.fundName}</h4>
                    <p style="font-size:0.84rem; color:var(--text-muted); margin-bottom:0.8rem;">${f.workDescription}</p>

                    <div style="margin-top:0.8rem;">
                        <div style="display:flex; justify-content:space-between; font-size:0.82rem; font-weight:600;">
                            <span>Utilized: ${utilPct}%</span>
                            <span style="color:var(--accent-cyan);">Spent: ₹${f.spentAmount} Lakhs / Allocated: ₹${f.allocatedAmount} Lakhs</span>
                        </div>
                        <div class="progress-bar-bg">
                            <div class="progress-bar-fill" style="width: ${utilPct}%;"></div>
                        </div>
                        <div style="font-size:0.78rem; color:var(--text-muted); text-align:right;">
                            Remaining Unspent Balance: <strong style="color:var(--accent-green);">₹${f.remainingAmount} Lakhs</strong>
                        </div>
                    </div>
                `;
                grid.appendChild(card);
            });
        });
}

// 3. NOTICES
function loadNotices() {
    fetch('/api/v1/community/notices')
        .then(res => res.json())
        .then(notices => {
            const container = document.getElementById('noticesListContainer');
            if (!container) return;
            container.innerHTML = '';
            notices.forEach(n => {
                const card = document.createElement('div');
                card.className = `notice-card ${n.priority}`;
                card.innerHTML = `
                    <div class="notice-header">
                        <span class="notice-tag ${n.priority}">${n.priority} ALERT</span>
                        <span style="font-size:0.8rem; color:var(--text-muted);"><i class="fa-solid fa-calendar"></i> ${n.noticeDate}</span>
                    </div>
                    <h4>${n.title}</h4>
                    <p>${n.details}</p>
                    <div class="notice-footer">
                        <span>Issued by: <strong>${n.postedBy}</strong></span>
                        <span class="cat-badge">${n.category}</span>
                    </div>
                `;
                container.appendChild(card);
            });
        });
}

// 4. GRIEVANCES
function handleGrievanceSubmit(event) {
    event.preventDefault();
    const category = document.getElementById('grvCategory').value;
    const residentName = document.getElementById('grvName').value.trim();
    const residentPhone = document.getElementById('grvPhone').value.trim();
    const location = document.getElementById('grvLocation').value.trim();
    const description = document.getElementById('grvDescription').value.trim();

    fetch('/api/v1/community/grievances', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ category, residentName, residentPhone, location, description })
    })
    .then(res => res.json())
    .then(data => {
        const resultBox = document.getElementById('grievanceSubmitResult');
        resultBox.style.display = 'block';
        resultBox.innerHTML = `
            <i class="fa-solid fa-circle-check"></i> <strong>Grievance Submitted Successfully!</strong><br>
            Your Tracking ID is: <strong style="font-size: 1.1rem; color: var(--accent-gold);">${data.trackingId}</strong><br>
            <span style="font-size: 0.82rem; color: var(--text-muted);">Please save this ID to track issue resolution status. Assigned to Gram Sachivalayam team.</span>
        `;
        document.getElementById('grievanceForm').reset();
        loadRecentGrievances();
    });
}

function trackGrievanceStatus() {
    const trackId = document.getElementById('trackIdInput').value.trim();
    const displayBox = document.getElementById('trackResultBox');

    if (!trackId) {
        alert('Please enter a tracking ID.');
        return;
    }

    fetch(`/api/v1/community/grievances/${trackId}`)
        .then(res => {
            if (!res.ok) throw new Error('Tracking ID not found');
            return res.json();
        })
        .then(g => {
            displayBox.style.display = 'block';
            displayBox.innerHTML = `
                <div style="margin-bottom:0.6rem;"><strong>Tracking ID:</strong> ${g.trackingId} (${g.category})</div>
                <div><strong>Status:</strong> <span class="tag-badge glow">${g.status}</span></div>
                <div style="font-size:0.85rem; margin-top:0.4rem;"><strong>Description:</strong> ${g.description}</div>
                <div style="font-size:0.82rem; color:var(--text-muted); margin-top:0.4rem;">
                    Submitted: ${g.submittedAt} | Location: ${g.location}<br>
                    ${g.assignedOfficial ? `Assigned to: <strong>${g.assignedOfficial}</strong><br>` : ''}
                    ${g.resolutionNotes ? `<span style="color:var(--accent-green);">Resolution: ${g.resolutionNotes}</span>` : ''}
                </div>
            `;
        })
        .catch(() => {
            displayBox.style.display = 'block';
            displayBox.innerHTML = `<span style="color:var(--accent-red);"><i class="fa-solid fa-circle-xmark"></i> Tracking ID '${trackId}' not found. Please check and try again.</span>`;
        });
}

function loadRecentGrievances() {
    fetch('/api/v1/community/grievances')
        .then(res => res.json())
        .then(list => {
            const container = document.getElementById('recentGrievancesList');
            if (!container) return;
            container.innerHTML = '';
            list.slice(0, 5).forEach(g => {
                const statusColor = g.status === 'RESOLVED' ? 'var(--accent-green)' : 'var(--accent-amber)';
                const div = document.createElement('div');
                div.className = 'cq-item';
                div.innerHTML = `
                    <div>
                        <strong>${g.trackingId} — ${g.category}</strong>
                        <span class="subtext">${g.location} (${g.submittedAt})</span>
                    </div>
                    <span style="color: ${statusColor}; font-weight: 600; font-size: 0.8rem;">${g.status}</span>
                `;
                container.appendChild(div);
            });
        });
}

// 5. RBK STOCKS
function loadRbkStocks() {
    fetch('/api/v1/community/rbk-stock')
        .then(res => res.json())
        .then(stocks => {
            const tbody = document.getElementById('rbkStockTableBody');
            if (!tbody) return;
            tbody.innerHTML = '';
            stocks.forEach(s => {
                const statusCss = s.rbkStatus === 'IN_STOCK' ? 'color:var(--accent-green);' : 'color:var(--accent-amber);';
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td><strong>${s.itemName}</strong></td>
                    <td>${s.category}</td>
                    <td><strong style="font-size:1.1rem; color:var(--text-bright);">${s.stockBags} Bags</strong></td>
                    <td>₹${s.pricePerBag}</td>
                    <td><span style="${statusCss} font-weight:600;"><i class="fa-solid fa-circle-check"></i> ${s.rbkStatus}</span></td>
                    <td><a href="tel:+919490211223" class="btn btn-sm btn-success"><i class="fa-solid fa-phone"></i> Call RBK</a></td>
                `;
                tbody.appendChild(tr);
            });
        });
}

// 6. BAZAAR MARKETPLACE
function loadMarketplace() {
    fetch(`/api/v1/community/marketplace?type=${activeBazaarFilter}`)
        .then(res => res.json())
        .then(items => {
            const grid = document.getElementById('bazaarGrid');
            if (!grid) return;
            grid.innerHTML = '';
            items.forEach(item => {
                const card = document.createElement('div');
                card.className = 'record-card';
                card.innerHTML = `
                    <div>
                        <div class="card-top">
                            <span class="cat-badge">${item.listingType}</span>
                            <span style="color:var(--accent-gold); font-weight:700;">${item.price}</span>
                        </div>
                        <h3>${item.title}</h3>
                        <p style="font-size:0.85rem; color:var(--text-muted); margin-bottom:0.8rem;">${item.description}</p>
                    </div>
                    <div>
                        <div style="font-size:0.8rem; color:var(--text-muted); margin-bottom:0.6rem;">
                            Seller: <strong>${item.sellerName}</strong> | Locality: ${item.location}
                        </div>
                        <div style="display:flex; gap:0.5rem;">
                            <a href="tel:${item.sellerPhone}" class="btn btn-sm btn-primary flex-1" style="justify-content:center;"><i class="fa-solid fa-phone"></i> Call Seller</a>
                            <a href="https://wa.me/${item.sellerPhone.replace(/[^0-9]/g,'')}" target="_blank" class="btn btn-sm btn-success"><i class="fa-brands fa-whatsapp"></i> Chat</a>
                        </div>
                    </div>
                `;
                grid.appendChild(card);
            });
        });
}

function filterBazaar(typeKey) {
    activeBazaarFilter = typeKey;
    document.querySelectorAll('#tabBazaar .bazaar-btn').forEach(btn => btn.classList.remove('active'));
    if (event && event.currentTarget) event.currentTarget.classList.add('active');
    loadMarketplace();
}

function openPostAdModal() {
    document.getElementById('postAdModal').style.display = 'flex';
}

function closePostAdModal() {
    document.getElementById('postAdModal').style.display = 'none';
}

function handlePostAdSubmit(event) {
    event.preventDefault();
    const title = document.getElementById('adTitle').value;
    const listingType = document.getElementById('adCategory').value;
    const price = document.getElementById('adPrice').value;
    const sellerName = document.getElementById('adName').value;
    const sellerPhone = document.getElementById('adPhone').value;
    const description = document.getElementById('adDescription').value;

    fetch('/api/v1/community/marketplace', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title, listingType, price, sellerName, sellerPhone, description, location: 'Chilakacherla' })
    })
    .then(res => res.json())
    .then(() => {
        closePostAdModal();
        loadMarketplace();
        alert('Your marketplace listing has been posted successfully!');
    });
}

// 7. CRAFTSMEN DIRECTORY
function loadCraftsmen() {
    fetch('/api/v1/community/craftsmen')
        .then(res => res.json())
        .then(list => {
            const grid = document.getElementById('craftsmenGrid');
            if (!grid) return;
            grid.innerHTML = '';
            list.forEach(c => {
                const card = document.createElement('div');
                card.className = 'craftsman-card';
                card.innerHTML = `
                    <div>
                        <div class="cm-trade">${c.trade}</div>
                        <h4 style="color:var(--text-bright);">${c.name}</h4>
                        <div style="font-size:0.78rem; color:var(--text-muted);">${c.experienceYears} Years Exp | Locality: ${c.locality}</div>
                        <div class="cm-rating"><i class="fa-solid fa-star"></i> ${c.rating} / 5.0 Rating</div>
                    </div>
                    <a href="tel:${c.phone}" class="btn btn-sm btn-primary"><i class="fa-solid fa-phone"></i> Call</a>
                `;
                grid.appendChild(card);
            });
        });
}

// 8. BLOOD DONORS
function loadBloodDonors() {
    fetch(`/api/v1/community/blood-donors?group=${activeBloodGroupFilter}`)
        .then(res => res.json())
        .then(donors => {
            const container = document.getElementById('donorList');
            if (!container) return;
            container.innerHTML = '';
            if (donors.length === 0) {
                container.innerHTML = '<div style="color:var(--text-muted); padding:1rem;">No volunteer blood donors listed for this group yet.</div>';
                return;
            }

            donors.forEach(d => {
                const card = document.createElement('div');
                card.className = 'donor-card';
                card.innerHTML = `
                    <div style="display:flex; align-items:center; gap:0.9rem;">
                        <div class="blood-tag">${d.bloodGroup}</div>
                        <div>
                            <strong style="color:var(--text-bright);">${d.donorName}</strong> (${d.age} Yrs)
                            <div style="font-size:0.78rem; color:var(--text-muted);">${d.locality} | Verified Resident</div>
                        </div>
                    </div>
                    <a href="tel:${d.phone}" class="btn btn-sm btn-danger"><i class="fa-solid fa-phone"></i> Call Donor</a>
                `;
                container.appendChild(card);
            });
        });
}

function filterBloodDonors() {
    activeBloodGroupFilter = document.getElementById('bloodGroupSelect').value;
    loadBloodDonors();
}

function handleDonorRegister(event) {
    event.preventDefault();
    const name = document.getElementById('donorName').value;
    const bloodGroup = document.getElementById('donorBloodGroup').value;
    const age = document.getElementById('donorAge').value;
    const phone = document.getElementById('donorPhone').value;

    fetch('/api/v1/community/blood-donors', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name, bloodGroup, age, phone, locality: 'Chilakacherla' })
    })
    .then(res => res.json())
    .then(() => {
        document.getElementById('donorForm').reset();
        loadBloodDonors();
        alert('Thank you! You have been registered as a volunteer blood donor.');
    });
}

// 9. JOBS
function loadJobs() {
    fetch('/api/v1/public/village/jobs')
        .then(res => res.json())
        .then(jobs => {
            const container = document.getElementById('jobsListContainer');
            if (!container) return;
            container.innerHTML = '';
            jobs.forEach(j => {
                const card = document.createElement('div');
                card.className = 'record-card';
                card.style.marginBottom = '1rem';
                card.innerHTML = `
                    <div class="card-top">
                        <span class="cat-badge">${j.jobType}</span>
                        <span style="font-size:0.8rem; color:var(--accent-amber);">Deadline: ${j.deadline}</span>
                    </div>
                    <h3>${j.jobTitle}</h3>
                    <p style="font-size:0.85rem; color:var(--accent-cyan); font-weight:600;">${j.organization}</p>
                    <p style="font-size:0.82rem; color:var(--text-muted); margin-top:0.4rem;">Qualification: ${j.qualification} | Location: ${j.location}</p>
                    <a href="${j.applyLink}" target="_blank" class="btn btn-sm btn-primary" style="margin-top:0.8rem; display:inline-flex;"><i class="fa-solid fa-paper-plane"></i> Apply Online</a>
                `;
                container.appendChild(card);
            });
        });
}

// 10. VOTER RECORDS & WARD MEMBERS
function loadVoterRecords() {
    fetch('/api/v1/community/voters')
        .then(res => res.json())
        .then(voters => {
            const tbody = document.getElementById('voterTableBody');
            if (!tbody) return;
            tbody.innerHTML = '';
            voters.forEach(v => {
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td><strong>Ward ${v.wardNo}</strong></td>
                    <td>${v.habitation}</td>
                    <td><strong style="color:var(--accent-blue);">Booth #${v.pollingStationNo}</strong> — ${v.pollingStationName}</td>
                    <td><strong style="font-size:1.1rem; color:var(--text-bright);">${v.totalVoters} Voters</strong></td>
                    <td><span style="color:var(--accent-green);">${v.maleVoters} M</span> / <span style="color:var(--accent-purple);">${v.femaleVoters} F</span></td>
                    <td><strong>${v.bloName}</strong><br><span style="font-size:0.78rem; color:var(--text-muted);">${v.bloPhone}</span></td>
                    <td><a href="tel:${v.bloPhone}" class="btn btn-sm btn-primary"><i class="fa-solid fa-phone"></i> Call BLO</a></td>
                `;
                tbody.appendChild(tr);
            });
        });
}

function loadWardMembers() {
    fetch('/api/v1/community/ward-members')
        .then(res => res.json())
        .then(members => {
            const grid = document.getElementById('wardMembersGrid');
            if (!grid) return;
            grid.innerHTML = '';
            members.forEach(m => {
                const card = document.createElement('div');
                card.className = 'craftsman-card';
                card.innerHTML = `
                    <div>
                        <div class="cm-trade">${m.role}</div>
                        <h4 style="color:var(--text-bright); font-size:1rem;">${m.memberName}</h4>
                        <div style="font-size:0.8rem; color:var(--text-muted);">${m.wardNo > 0 ? `Ward ${m.wardNo} — ` : ''}${m.habitation}</div>
                    </div>
                    <a href="tel:${m.phone}" class="btn btn-sm btn-success"><i class="fa-solid fa-phone"></i> Call Representative</a>
                `;
                grid.appendChild(card);
            });
        });
}

function loadVoterMembers(query = '', wardNo = 0) {
    let url = '/api/v1/community/voter-members';
    const params = [];
    if (query) params.push(`query=${encodeURIComponent(query)}`);
    if (wardNo && wardNo > 0) params.push(`wardNo=${wardNo}`);
    if (params.length > 0) url += '?' + params.join('&');

    fetch(url)
        .then(res => res.json())
        .then(members => {
            const tbody = document.getElementById('voterMemberTableBody');
            if (!tbody) return;
            tbody.innerHTML = '';

            if (!members || members.length === 0) {
                tbody.innerHTML = `
                    <tr>
                        <td colspan="7" style="text-align: center; padding: 2rem; color: var(--text-muted);">
                            <i class="fa-solid fa-user-slash" style="font-size: 1.8rem; margin-bottom: 0.5rem; display: block;"></i>
                            No resident voters found matching the search criteria.
                        </td>
                    </tr>
                `;
                return;
            }

            members.forEach(m => {
                const genderColor = m.gender === 'Male' ? 'var(--accent-green)' : 'var(--accent-purple)';
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td><strong style="color:var(--accent-gold); font-family:monospace; font-size:0.9rem;">${m.epicNo}</strong></td>
                    <td><strong style="color:var(--text-bright); font-size:0.95rem;">${m.voterName}</strong></td>
                    <td>${m.relationName}</td>
                    <td><span class="tag-badge glow" style="font-size:0.75rem;">${m.houseNo}</span></td>
                    <td><span style="color:${genderColor}; font-weight:600;">${m.gender}</span> (${m.age} Yrs)</td>
                    <td>Ward ${m.wardNo} — ${m.habitation}</td>
                    <td><strong style="color:var(--accent-blue);">Booth #${m.pollingStationNo}</strong></td>
                `;
                tbody.appendChild(tr);
            });
        });
}

function filterVoterMembers() {
    const query = document.getElementById('voterSearchInput').value.trim();
    const wardNo = parseInt(document.getElementById('voterWardFilter').value) || 0;
    loadVoterMembers(query, wardNo);
}

// 11. JOB HOLDERS & EDUCATED YOUTH TALENT POOL
function loadJobHolders() {
    fetch('/api/v1/community/job-holders')
        .then(res => res.json())
        .then(list => {
            const grid = document.getElementById('jobHoldersGrid');
            if (!grid) return;
            grid.innerHTML = '';
            list.forEach(emp => {
                const card = document.createElement('div');
                card.className = 'craftsman-card';
                card.innerHTML = `
                    <div>
                        <div class="cm-trade" style="color:var(--accent-blue);">${emp.employeeType} — ${emp.department}</div>
                        <h4 style="color:var(--text-bright); font-size:1rem;">${emp.name}</h4>
                        <div style="font-size:0.84rem; color:var(--accent-green); font-weight:600;">${emp.designation}</div>
                        <div style="font-size:0.78rem; color:var(--text-muted); margin-top:0.2rem;">Location: ${emp.workLocation} | Habitation: ${emp.habitation}</div>
                    </div>
                    <a href="tel:${emp.phone}" class="btn btn-sm btn-primary"><i class="fa-solid fa-phone"></i> Call</a>
                `;
                grid.appendChild(card);
            });
        });
}

function loadEducatedYouth() {
    fetch('/api/v1/community/educated-youth')
        .then(res => res.json())
        .then(candidates => {
            const tbody = document.getElementById('educatedYouthTableBody');
            if (!tbody) return;
            tbody.innerHTML = '';
            candidates.forEach(c => {
                const statusCss = c.status === 'EMPLOYED' ? 'var(--accent-green)' : (c.status === 'LOOKING_FOR_JOB' ? 'var(--accent-cyan)' : 'var(--accent-amber)');
                const statusLabel = c.status.replace(/_/g, ' ');
                const tr = document.createElement('tr');
                tr.innerHTML = `
                    <td><strong style="color:var(--text-bright); font-size:0.95rem;">${c.name}</strong></td>
                    <td><strong style="color:var(--accent-blue);">${c.degree}</strong> (${c.specialization})</td>
                    <td><span class="tag-badge glow" style="font-size:0.78rem;">Year ${c.passoutYear}</span></td>
                    <td><span style="font-size:0.82rem; color:var(--text-main);">${c.skills}</span></td>
                    <td><span style="color:${statusCss}; font-weight:700; font-size:0.82rem;"><i class="fa-solid fa-user-check"></i> ${statusLabel}</span></td>
                    <td>${c.habitation}</td>
                    <td><a href="tel:${c.phone}" class="btn btn-sm btn-success"><i class="fa-solid fa-phone"></i> Contact</a></td>
                `;
                tbody.appendChild(tr);
            });
        });
}

window.closeFounderModal = function() {
    const modal = document.getElementById('founderModal');
    if (modal) {
        modal.style.setProperty('display', 'none', 'important');
    }
};





