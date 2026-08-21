Write-Host "=== TESTING CHILAKACHERLA SCHEMES & PANCHAYAT FUNDS APIS ==="

$schemes = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/community/schemes"
Write-Host "`n1. GOVERNMENT SCHEMES ECOSYSTEM ($($schemes.Count)):"
foreach ($s in $schemes) {
    Write-Host "- [$($s.status)] $($s.schemeName) ($($s.category))"
    Write-Host "  Benefit: $($s.financialBenefit)"
    Write-Host "  Deadline: $($s.applicationDeadline) | Sanctioned Beneficiaries in Village: $($s.beneficiaryCount)"
    Write-Host "  Eligibility: $($s.eligibilityCriteria)"
    Write-Host "  --------------------------------------------------------"
}

$funds = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/community/funds"
Write-Host "`n2. PANCHAYAT DEVELOPMENT FUNDS ALLOCATION & SPENDING ($($funds.Count)):"
foreach ($f in $funds) {
    $pct = [Math]::Round(($f.spentAmount / $f.allocatedAmount) * 100)
    Write-Host "- FY $($f.financialYear) | $($f.fundName) [$($f.status)]"
    Write-Host "  Allocated: ₹$($f.allocatedAmount) Lakhs | Spent: ₹$($f.spentAmount) Lakhs ($pct%) | Remaining: ₹$($f.remainingAmount) Lakhs"
    Write-Host "  Work: $($f.workDescription)"
    Write-Host "  --------------------------------------------------------"
}

# Test Scheme Application Submission
$appBody = @{
    schemeName = "Dr. YSR Rythu Bharosa / PM-KISAN"
    applicantName = "K. Rama Rao"
    applicantAadhar = "987654321012"
    phone = "+91 94401 23456"
    rationCardNo = "WAP1523331001"
} | ConvertTo-Json

$appResult = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/community/schemes/apply" -Method Post -ContentType "application/json" -Body $appBody
Write-Host "`n3. SUBMIT SCHEME APPLICATION TEST:"
Write-Host "- Application ID: $($appResult.applicationId)"
Write-Host "- Applicant: $($appResult.applicantName)"
Write-Host "- Scheme: $($appResult.schemeName)"
Write-Host "- Status: $($appResult.status)"

# Track Scheme Application
$trackResult = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/community/schemes/track/$($appResult.applicationId)"
Write-Host "`n4. TRACK SCHEME APPLICATION TEST:"
Write-Host "- Found Tracking Record: $($trackResult.applicationId) -> Status: $($trackResult.status)"
