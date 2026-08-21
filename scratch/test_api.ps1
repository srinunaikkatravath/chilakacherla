$stats = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/public/village/stats"
Write-Host "=== VILLAGE STATS ==="
$stats | Format-List

$records = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/public/village/records"
Write-Host "=== VERIFIED RECORDS ($($records.Count)) ==="
foreach ($r in $records) {
    Write-Host "#$($r.id) [$($r.category)] $($r.title) - $($r.sourceName) (Score: $($r.confidenceScore)%) [$($r.dataLayer)]"
}

$jobs = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/public/village/jobs"
Write-Host "=== ACTIVE JOBS ($($jobs.Count)) ==="
foreach ($j in $jobs) {
    Write-Host "- $($j.jobTitle) at $($j.organization) ($($j.jobType)) - Deadline: $($j.deadline)"
}
