// Admin Dashboard Script

let activeAdminStatus = 'PENDING';
let currentConflictsList = [];

function initAdminConsole() {
    fetchAdminCounts();
    loadAdminTab(activeAdminStatus);
}

function fetchAdminCounts() {
    fetch('/api/v1/public/village/stats')
        .then(res => res.json())
        .then(data => {
            document.getElementById('cntPending').textContent = data.pendingVerification || 0;
            document.getElementById('cntDiscovered').textContent = data.discoveredRecords || 0;
            document.getElementById('cntConflicts').textContent = data.conflictsCount || 0;
            document.getElementById('cntVerified').textContent = data.totalVerifiedRecords || 0;
        });

    fetch('/api/v1/research/duplicates')
        .then(res => res.json())
        .then(data => {
            document.getElementById('cntDuplicates').textContent = data.length || 0;
        });
}

function switchAdminTab(statusKey) {
    activeAdminStatus = statusKey;
    document.querySelectorAll('.admin-tab').forEach(t => t.classList.remove('active'));

    const tabMap = {
        'PENDING': 'tabPending',
        'DISCOVERED': 'tabDiscovered',
        'CONFLICT': 'tabConflicts',
        'DUPLICATES': 'tabDuplicates',
        'VERIFIED': 'tabVerified',
        'SCHEDULER': 'tabScheduler'
    };

    if (tabMap[statusKey]) {
        document.getElementById(tabMap[statusKey]).classList.add('active');
    }

    loadAdminTab(statusKey);
}

function loadAdminTab(statusKey) {
    const container = document.getElementById('adminTabContent');
    container.innerHTML = '<div style="text-align: center; padding: 2rem; color: var(--text-muted);"><i class="fa-solid fa-spinner fa-spin"></i> Loading queue...</div>';

    if (statusKey === 'CONFLICT') {
        loadConflictsTab();
        return;
    } else if (statusKey === 'DUPLICATES') {
        loadDuplicatesTab();
        return;
    } else if (statusKey === 'SCHEDULER') {
        loadSchedulerTab();
        return;
    }

    fetch(`/api/v1/research/records?status=${statusKey}`)
        .then(res => res.json())
        .then(records => {
            renderAdminRecordsTable(records, statusKey);
        })
        .catch(err => {
            container.innerHTML = '<div style="color: var(--accent-red);">Error loading records.</div>';
        });
}

function renderAdminRecordsTable(records, statusKey) {
    const container = document.getElementById('adminTabContent');
    if (!records || records.length === 0) {
        container.innerHTML = `
            <div style="text-align: center; padding: 3rem; color: var(--text-muted);">
                <i class="fa-solid fa-check-double" style="font-size: 2rem; margin-bottom: 0.8rem; display: block;"></i>
                <p>No records found in <strong>${statusKey}</strong> status.</p>
            </div>
        `;
        return;
    }

    let html = `
        <div class="table-responsive">
            <table class="data-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Category</th>
                        <th>Entity Name</th>
                        <th>Record Title</th>
                        <th>Source</th>
                        <th>Trust Rank</th>
                        <th>Confidence</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
    `;

    records.forEach(r => {
        let actionButtons = '';
        if (statusKey === 'PENDING' || statusKey === 'DISCOVERED') {
            actionButtons = `
                <button class="btn btn-success btn-sm" onclick="approveRecord(${r.id})"><i class="fa-solid fa-check"></i> Approve</button>
                <button class="btn btn-danger btn-sm" onclick="rejectRecord(${r.id})"><i class="fa-solid fa-xmark"></i> Reject</button>
            `;
        } else if (statusKey === 'VERIFIED') {
            actionButtons = `<span style="color: var(--accent-green); font-weight: 600;"><i class="fa-solid fa-circle-check"></i> Published</span>`;
        } else {
            actionButtons = `<span style="color: var(--text-muted);">Resolved</span>`;
        }

        html += `
            <tr>
                <td>#${r.id}</td>
                <td><span class="cat-badge">${r.category}</span></td>
                <td><strong>${r.entity}</strong></td>
                <td>${r.title}</td>
                <td><a href="${r.sourceUrl || '#'}" target="_blank" class="source-link">${r.sourceName || 'Source'}</a></td>
                <td><span style="color: var(--accent-gold);">${r.trustLevel || 'L4'}</span></td>
                <td><span style="color: var(--accent-green); font-weight: 600;">${r.confidenceScore}%</span></td>
                <td>${actionButtons}</td>
            </tr>
        `;
    });

    html += `</tbody></table></div>`;
    container.innerHTML = html;
}

function approveRecord(id) {
    fetch(`/api/v1/research/approve/${id}`, { method: 'POST' })
        .then(res => res.json())
        .then(() => {
            fetchAdminCounts();
            loadAdminTab(activeAdminStatus);
        });
}

function rejectRecord(id) {
    fetch(`/api/v1/research/reject/${id}`, { method: 'POST' })
        .then(res => res.json())
        .then(() => {
            fetchAdminCounts();
            loadAdminTab(activeAdminStatus);
        });
}

function loadConflictsTab() {
    const container = document.getElementById('adminTabContent');
    fetch('/api/v1/research/conflicts?resolved=false')
        .then(res => res.json())
        .then(conflicts => {
            currentConflictsList = conflicts;
            if (!conflicts || conflicts.length === 0) {
                container.innerHTML = `
                    <div style="text-align: center; padding: 3rem; color: var(--text-muted);">
                        <i class="fa-solid fa-shield-check" style="font-size: 2rem; margin-bottom: 0.8rem; display: block; color: var(--accent-green);"></i>
                        <p>No active data conflicts detected.</p>
                    </div>
                `;
                return;
            }

            let html = `
                <div class="table-responsive">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>Conflict ID</th>
                                <th>Disagreed Property</th>
                                <th>Source A (Existing)</th>
                                <th>Source B (Discovered)</th>
                                <th>Status</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
            `;

            conflicts.forEach(c => {
                html += `
                    <tr>
                        <td>#${c.id}</td>
                        <td><strong>${c.propertyName}</strong></td>
                        <td>${c.sourceAName} (Value: <em>${c.sourceAValue}</em>)</td>
                        <td>${c.sourceBName} (Value: <em>${c.sourceBValue}</em>)</td>
                        <td><span style="color: var(--accent-amber); font-weight: 600;"><i class="fa-solid fa-triangle-exclamation"></i> Disagreement</span></td>
                        <td>
                            <button class="btn btn-accent btn-sm" onclick="openConflictModal(${c.id})">
                                <i class="fa-solid fa-scale-balanced"></i> Compare & Resolve
                            </button>
                        </td>
                    </tr>
                `;
            });

            html += `</tbody></table></div>`;
            container.innerHTML = html;
        });
}

function openConflictModal(conflictId) {
    const conflict = currentConflictsList.find(c => c.id === conflictId);
    if (!conflict) return;

    const modal = document.getElementById('conflictModal');
    const body = document.getElementById('conflictModalBody');

    body.innerHTML = `
        <div style="margin-bottom: 1rem; color: var(--text-muted); font-size: 0.9rem;">
            <strong>Conflict Property:</strong> <span style="color: var(--text-bright);">${conflict.propertyName}</span>
        </div>
        <div class="conflict-grid">
            <div class="conflict-option">
                <div style="font-weight: 700; color: var(--accent-gold); margin-bottom: 0.5rem;">Source A (Official / Verified)</div>
                <p><strong>Source Name:</strong> ${conflict.sourceAName}</p>
                <p><strong>Trust Level:</strong> ${conflict.sourceATrustLevel}</p>
                <p><strong>Retrieved Date:</strong> ${conflict.sourceADate}</p>
                <div style="margin-top: 0.8rem; background: #000; padding: 0.8rem; border-radius: 8px; font-family: monospace; font-size: 0.85rem; color: var(--accent-green);">
                    ${conflict.sourceAValue}
                </div>
                <button class="btn btn-success btn-sm" style="margin-top: 1rem; width: 100%; justify-content: center;" onclick="submitConflictDecision(${conflict.id}, ${conflict.recordAId}, 'Selected Source A (Highest Trust)')">
                    Select Source A Value
                </button>
            </div>

            <div class="conflict-option">
                <div style="font-weight: 700; color: var(--accent-cyan); margin-bottom: 0.5rem;">Source B (Candidate Discovered)</div>
                <p><strong>Source Name:</strong> ${conflict.sourceBName}</p>
                <p><strong>Trust Level:</strong> ${conflict.sourceBTrustLevel}</p>
                <p><strong>Retrieved Date:</strong> ${conflict.sourceBDate}</p>
                <div style="margin-top: 0.8rem; background: #000; padding: 0.8rem; border-radius: 8px; font-family: monospace; font-size: 0.85rem; color: var(--accent-cyan);">
                    ${conflict.sourceBValue}
                </div>
                <button class="btn btn-primary btn-sm" style="margin-top: 1rem; width: 100%; justify-content: center;" onclick="submitConflictDecision(${conflict.id}, ${conflict.recordBId}, 'Selected Source B (Updated Candidate)')">
                    Select Source B Value
                </button>
            </div>
        </div>
    `;

    modal.style.display = 'flex';
}

function closeConflictModal() {
    document.getElementById('conflictModal').style.display = 'none';
}

function submitConflictDecision(conflictId, selectedRecordId, decisionText) {
    fetch(`/api/v1/research/conflicts/resolve?conflictId=${conflictId}&selectedRecordId=${selectedRecordId}&decision=${encodeURIComponent(decisionText)}`, {
        method: 'POST'
    })
    .then(res => res.json())
    .then(() => {
        closeConflictModal();
        fetchAdminCounts();
        loadAdminTab('CONFLICT');
    });
}

function loadDuplicatesTab() {
    const container = document.getElementById('adminTabContent');
    fetch('/api/v1/research/duplicates')
        .then(res => res.json())
        .then(groups => {
            if (!groups || groups.length === 0) {
                container.innerHTML = `
                    <div style="text-align: center; padding: 3rem; color: var(--text-muted);">
                        <i class="fa-solid fa-circle-check" style="font-size: 2rem; margin-bottom: 0.8rem; display: block; color: var(--accent-green);"></i>
                        <p>No entity spelling duplicates detected.</p>
                    </div>
                `;
                return;
            }

            let html = `
                <div class="table-responsive">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>Match ID</th>
                                <th>Entity Group Variant</th>
                                <th>Similarity Score</th>
                                <th>Primary Record ID</th>
                                <th>Candidate Duplicate ID</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
            `;

            groups.forEach(g => {
                html += `
                    <tr>
                        <td>#${g.id}</td>
                        <td><strong>${g.groupName}</strong></td>
                        <td><span style="color: var(--accent-green); font-weight: 600;">${g.similarityScore}%</span></td>
                        <td>Record #${g.primaryRecordId}</td>
                        <td>Record #${g.duplicateRecordId}</td>
                        <td><span class="tag-badge glow">${g.status}</span></td>
                        <td>
                            ${g.status === 'POSSIBLE_MATCH' ? `
                                <button class="btn btn-success btn-sm" onclick="confirmDuplicate(${g.id}, 'CONFIRMED')"><i class="fa-solid fa-link"></i> Confirm Merge</button>
                                <button class="btn btn-danger btn-sm" onclick="confirmDuplicate(${g.id}, 'SEPARATED')"><i class="fa-solid fa-link-slash"></i> Separate</button>
                            ` : `<span style="color: var(--text-muted);">${g.status}</span>`}
                        </td>
                    </tr>
                `;
            });

            html += `</tbody></table></div>`;
            container.innerHTML = html;
        });
}

function confirmDuplicate(groupId, action) {
    fetch(`/api/v1/research/duplicates/confirm?duplicateGroupId=${groupId}&action=${action}`, { method: 'POST' })
        .then(res => res.json())
        .then(() => {
            loadDuplicatesTab();
        });
}

function loadSchedulerTab() {
    const container = document.getElementById('adminTabContent');
    fetch('/api/v1/research/scheduler')
        .then(res => res.json())
        .then(schedules => {
            let html = `
                <div class="table-responsive">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>Schedule ID</th>
                                <th>Dataset Category</th>
                                <th>Category Enum</th>
                                <th>Crawl Frequency</th>
                                <th>Last Checked</th>
                                <th>Next Scheduled Check</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
            `;

            schedules.forEach(s => {
                html += `
                    <tr>
                        <td>#${s.id}</td>
                        <td><strong>${s.datasetName}</strong></td>
                        <td><span class="cat-badge">${s.category}</span></td>
                        <td><span style="color: var(--accent-cyan); font-weight: 600;">${s.frequency}</span></td>
                        <td>${s.lastChecked || 'N/A'}</td>
                        <td>${s.nextCheck || 'N/A'}</td>
                        <td><span class="badge-dot pulse" style="display:inline-block; margin-right:4px;"></span> <span style="color: var(--accent-green); font-weight:600;">${s.status}</span></td>
                    </tr>
                `;
            });

            html += `</tbody></table></div>`;
            container.innerHTML = html;
        });
}

function runAiResearchAgent() {
    const query = document.getElementById('agentQueryInput').value.trim();
    const category = document.getElementById('agentCategorySelect').value;
    const logsBox = document.getElementById('agentLogsBox');
    const logsContent = document.getElementById('agentLogsContent');

    if (!query) {
        alert('Please enter a target search topic.');
        return;
    }

    logsBox.style.display = 'block';
    logsContent.textContent = 'Initializing AI Research Agent Pipeline... Searching public government & web indexes...';

    fetch(`/api/v1/research/agent/run?queryTopic=${encodeURIComponent(query)}&category=${encodeURIComponent(category)}`, {
        method: 'POST'
    })
    .then(res => res.json())
    .then(data => {
        logsContent.textContent = data.logs.join('\n');
        fetchAdminCounts();
    })
    .catch(err => {
        logsContent.textContent = 'Error running agent research pipeline.';
    });
}

function triggerManualCrawl() {
    fetch('/api/v1/research/scheduler/trigger', { method: 'POST' })
        .then(res => res.json())
        .then(data => {
            alert(data.message);
            fetchAdminCounts();
            if (activeAdminStatus === 'SCHEDULER') loadSchedulerTab();
        });
}
