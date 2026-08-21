Write-Host "=== TESTING CHILAKACHERLA COMMUNITY PORTAL APIS ==="

$notices = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/community/notices"
Write-Host "`n1. COMMUNITY NOTICES ($($notices.Count)):"
foreach ($n in $notices) {
    Write-Host "- [$($n.priority)] $($n.title) ($($n.noticeDate)) Issued by: $($n.postedBy)"
}

$grievances = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/community/grievances"
Write-Host "`n2. RESIDENT GRIEVANCES ($($grievances.Count)):"
foreach ($g in $grievances) {
    Write-Host "- Tracking ID: $($g.trackingId) | Category: $($g.category) | Status: $($g.status) | Resident: $($g.residentName)"
}

$stocks = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/community/rbk-stock"
Write-Host "`n3. RBK FERTILIZER & SEED STOCKS ($($stocks.Count)):"
foreach ($s in $stocks) {
    Write-Host "- $($s.itemName) ($($s.category)): $($s.stockBags) Bags @ ₹$($s.pricePerBag)/bag [$($s.rbkStatus)]"
}

$marketplace = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/community/marketplace"
Write-Host "`n4. GRAMA BAZAAR MARKETPLACE LISTINGS ($($marketplace.Count)):"
foreach ($m in $marketplace) {
    Write-Host "- [$($m.listingType)] $($m.title) - Price: $($m.price) | Seller: $($m.sellerName) ($($m.sellerPhone))"
}

$donors = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/community/blood-donors"
Write-Host "`n5. VOLUNTEER BLOOD DONORS ($($donors.Count)):"
foreach ($d in $donors) {
    Write-Host "- $($d.donorName) (Blood Group: $($d.bloodGroup), Age: $($d.age)) - Phone: $($d.phone)"
}

$craftsmen = Invoke-RestMethod -Uri "http://localhost:8080/api/v1/community/craftsmen"
Write-Host "`n6. CRAFTSMEN & TRADESMEN DIRECTORY ($($craftsmen.Count)):"
foreach ($c in $craftsmen) {
    Write-Host "- $($c.name) - Trade: $($c.trade) | Exp: $($c.experienceYears) Yrs | Rating: $($c.rating) | Phone: $($c.phone)"
}
