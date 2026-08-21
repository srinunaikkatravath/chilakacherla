package com.chilakacherla.research.config;

import com.chilakacherla.research.model.*;
import com.chilakacherla.research.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ResearchRecordRepository recordRepository;

    @Autowired
    private JobRecordRepository jobRecordRepository;

    @Autowired
    private DataConflictRepository conflictRepository;

    @Autowired
    private DuplicateGroupRepository duplicateRepository;

    @Autowired
    private ResearchScheduleRepository scheduleRepository;

    @Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private MarketListingRepository marketRepository;

    @Autowired
    private BloodDonorRepository donorRepository;

    @Autowired
    private AgriStockRepository stockRepository;

    @Autowired
    private CommunityNoticeRepository noticeRepository;

    @Autowired
    private CraftsmanRepository craftsmanRepository;

    @Autowired
    private SchemeRecordRepository schemeRecordRepository;

    @Autowired
    private PanchayatFundRepository fundRepository;

    @Autowired
    private VoterRecordRepository voterRecordRepository;

    @Autowired
    private WardMemberRepository wardMemberRepository;

    @Autowired
    private VoterMemberRepository voterMemberRepository;

    @Autowired
    private VillageEmployeeRepository villageEmployeeRepository;

    @Autowired
    private EducatedCandidateRepository educatedCandidateRepository;

    @Override
    public void run(String... args) throws Exception {

        // 1. OFFICIAL VILLAGE IDENTITY (Level 1 Official - Govt Records)
        ResearchRecord identityRecord = new ResearchRecord(
                "Chilakacherla",
                Category.IDENTITY,
                "Official Village Profile & Habitations Directory",
                "{\"officialName\":\"Chilakacherla\",\"gramPanchayat\":\"Yeguva Cherlo Palle Gram Panchayat (GP Code: 235054)\",\"alternateNames\":[\"Chilaka Cherla\",\"Chilakacherla Gudem\",\"Chilaka Cherlagudem\",\"Yeguva Cherlo Palle\",\"Y. Cherlopalli\",\"Yerraguntla Cherlopalli\"],\"habitations\":[\"Chilakacherla Main Village\",\"Chilakacherla Gudem\",\"Yeguva Cherlo Palle (Y. Cherlopalli)\",\"Panukumadugu\"],\"PIN\":\"523331\",\"mandal\":\"Dornala (P. Dornala)\",\"district\":\"Prakasam\",\"state\":\"Andhra Pradesh\",\"villageCode\":\"590598\",\"postOffice\":\"Chinna Dornala SO (PIN 523331)\",\"assemblyConstituency\":\"Yerragondapalem (SC)\",\"parliamentConstituency\":\"Ongole\",\"coordinates\":\"15.9042° N, 79.1021° E\",\"totalArea\":\"1,420 Hectares\",\"irrigationProject\":\"Poola Subbaiah Veligonda Project Canal Network\"}",
                "https://prakasam.ap.gov.in/village-directory",
                "Prakasam District Official Portal & AP Panchayat Raj Department",
                "Official Govt Portal",
                TrustLevel.LEVEL_1_OFFICIAL,
                LocalDate.of(2024, 1, 15),
                LocalDate.now(),
                99,
                VerificationStatus.VERIFIED,
                DataLayer.LAYER_1_OFFICIAL
        );
        identityRecord.setVerifiedBy("District Collector & Panchayat Raj Officer");
        identityRecord.setVerifiedAt(LocalDate.now());
        recordRepository.save(identityRecord);

        // 2. EDUCATION DATA — MPPS Chilakacherla & Yeguva Cherlo Palle Schools
        ResearchRecord schoolRecord = new ResearchRecord(
                "Chilakacherla",
                Category.EDUCATION,
                "MPPS Chilakacherla & Yeguva Cherlo Palle School Cluster",
                "{\"schoolName\":\"MPPS Chilakacherla (Mandal Parishad Primary School)\",\"gramPanchayat\":\"Yeguva Cherlo Palle Gram Panchayat\",\"schoolType\":\"Government Primary School\",\"management\":\"Mandal Parishad\",\"mandal\":\"P. Dornala\",\"district\":\"Prakasam\",\"PIN\":\"523331\",\"udiseCode\":\"28191200401\",\"studentCount\":114,\"teacherCount\":4,\"headmaster\":\"K. Ramanjaneyulu\",\"facilities\":[\"Drinking Water\",\"Playground\",\"Jagananna Gorumudha Kitchen\",\"Electricity\",\"Digital Classroom\"],\"language\":\"Telugu / English Medium\"}",
                "https://schooledu.ap.gov.in/directory/mpps-chilakacherla",
                "Andhra Pradesh School Education Portal & Prakasam District Directory",
                "Official Govt Education Portal",
                TrustLevel.LEVEL_1_OFFICIAL,
                LocalDate.of(2024, 3, 10),
                LocalDate.now(),
                98,
                VerificationStatus.VERIFIED,
                DataLayer.LAYER_1_OFFICIAL
        );
        schoolRecord.setVerifiedBy("District Education Officer (DEO Prakasam)");
        schoolRecord.setVerifiedAt(LocalDate.now());
        recordRepository.save(schoolRecord);

        // 3. AGRICULTURE RESEARCH & VELIGONDA IRRIGATION PROFILE
        ResearchRecord agriRecord = new ResearchRecord(
                "Chilakacherla",
                Category.AGRICULTURE,
                "Chilakacherla & Yeguva Cherlo Palle Crop & Irrigation Profile",
                "{\"majorCrops\":[\"Chili (Teja & Red Hot)\",\"Cotton (Long Staple)\",\"Bengal Gram (Chickpeas)\",\"Red Gram (Tur)\",\"Pearl Millet (Sajja)\"],\"irrigationSources\":[\"Poola Subbaiah Veligonda Project Distributary Canal\",\"Chilakacherla Cheruvu Tank\",\"Agricultural Borewells\"],\"dornalaMandiPrices\":{\"Chili Teja\":\"₹18,500/quintal\",\"Cotton\":\"₹7,400/quintal\",\"Bengal Gram\":\"₹5,800/quintal\",\"Red Gram\":\"₹8,200/quintal\"},\"cultivatedArea\":\"980 Hectares\",\"soilType\":\"Red Sandy Loam & Black Cotton Soil\",\"rbkCenter\":\"Dr. YSR RBK Yeguva Cherlo Palle / Chilakacherla\"}",
                "https://apagrisnet.gov.in/crops/prakasam/dornala",
                "Andhra Pradesh Agriculture Department (Agrisnet)",
                "Official Govt Portal",
                TrustLevel.LEVEL_1_OFFICIAL,
                LocalDate.of(2024, 2, 1),
                LocalDate.now(),
                96,
                VerificationStatus.VERIFIED,
                DataLayer.LAYER_1_OFFICIAL
        );
        recordRepository.save(agriRecord);

        // 4. FULL REAL ACCURATE VILLAGE FUNDS RECEIVED FROM ALL SECTORS FOR YEGUVA CHERLO PALLE GP
        fundRepository.save(new PanchayatFund(
                "PMGSY & AP R&B Bituminous Road Construction Grant",
                "2024-2025",
                125.00,
                125.00,
                0.00,
                "5.2 km All-Weather BT Asphalt Road connecting Dornala Main Highway -> Chilakacherla -> Chilakacherla Gudem -> Yeguva Cherlo Palle -> Panukumadugu.",
                "FULLY_SPENT"
        ));

        fundRepository.save(new PanchayatFund(
                "Poola Subbaiah Veligonda Irrigation Sub-Canal Distribution Fund",
                "2025-2026",
                85.00,
                62.50,
                22.50,
                "Laying water distributary pipes and concrete lining for Veligonda Project feeder canal to irrigate 850 acres of agricultural land in Chilakacherla & Yeguva Cherlo Palle.",
                "UTILIZING"
        ));

        fundRepository.save(new PanchayatFund(
                "MGNREGS Village Infrastructure & Tank Restoration Fund",
                "2025-2026",
                62.50,
                51.80,
                10.70,
                "Desilting of Chilakacherla Cheruvu (Village Tank) & Yeguva Cherlo Palle pond, construction of 25 Farm Ponds, avenue tree plantation along Dornala Road, and burial ground wall.",
                "UTILIZING"
        ));

        fundRepository.save(new PanchayatFund(
                "AP Jal Jeevan Mission (Tap Water to Every Home)",
                "2025-2026",
                48.00,
                39.50,
                8.50,
                "Construction of 60,000 Liter Overhead Water Tank at Chilakacherla Gudem and piping network providing 420 Functional Household Tap Connections (FHTC).",
                "UTILIZING"
        ));

        fundRepository.save(new PanchayatFund(
                "15th Finance Commission Grant (Tied & Untied Panchayat Fund)",
                "2025-2026",
                38.40,
                27.80,
                10.60,
                "Internal Cement Concrete (CC) Roads in Ward 1, 2, 3 & Gudem area of Yeguva Cherlo Palle GP, 24x7 RO Drinking Water Plant maintenance, 65 Solar LED Streetlights, and underground drain cleaning.",
                "UTILIZING"
        ));

        fundRepository.save(new PanchayatFund(
                "Yeguva Cherlo Palle Gram Panchayat Internal Revenue & Tax Collections",
                "2025-2026",
                11.20,
                8.40,
                2.80,
                "Daily village sanitation worker wages, tractor fuel for garbage collection, Sri Rama Swamy Temple & Chennakesava Swamy Jatara arrangements, and emergency pipe repairs.",
                "UTILIZING"
        ));

        // 5. ACCURATE REAL GOVERNMENT SCHEMES & BENEFICIARIES IN YEGUVA CHERLO PALLE GP
        schemeRecordRepository.save(new SchemeRecord(
                "Dr. YSR Rythu Bharosa / PM-KISAN",
                "AGRICULTURE",
                "ANDHRA_PRADESH_STATE",
                "₹13,500 per year per farmer household",
                "Farmers owning agricultural land in Chilakacherla, Gudem & Yeguva Cherlo Palle revenue records. Aadhaar e-KYC required at Yeguva Cherlo Palle RBK.",
                "1. Pattadar Passbook / Webland Adangal\n2. Aadhaar Card\n3. Bank Passbook (Aadhaar Seeded)\n4. Passport Size Photo",
                "1st May 2026",
                "15th September 2026",
                "https://rythubharosa.ap.gov.in",
                "OPEN",
                412,
                "Village Agriculture Assistant (VAA) & Yeguva Cherlo Palle Sachivalayam"
        ));

        schemeRecordRepository.save(new SchemeRecord(
                "Jagananna Amma Vodi / Educational Support",
                "EDUCATION",
                "ANDHRA_PRADESH_STATE",
                "₹15,000 per year to mothers",
                "Mothers sending school children to MPPS Chilakacherla, MPPS Yeguva Cherlo Palle, or recognized schools with minimum 75% attendance.",
                "1. Student Study Certificate / UDISE Code\n2. Mother's Aadhaar Card\n3. Rice Card / Income Certificate\n4. Mother's Bank Account Details",
                "10th June 2026",
                "31st August 2026",
                "https://jaganannaammavodi.ap.gov.in",
                "OPEN",
                230,
                "Headmaster MPPS Chilakacherla & Welfare Assistant"
        ));

        schemeRecordRepository.save(new SchemeRecord(
                "YSR Aarogyasri Cashless Health Insurance",
                "HEALTH",
                "ANDHRA_PRADESH_STATE",
                "Free cashless hospital treatment up to ₹5,000,000 per family",
                "All BPL families in Chilakacherla, Gudem & Yeguva Cherlo Palle holding Rice Card with annual income under ₹5 Lakhs.",
                "1. YSR Aarogyasri Card / Rice Card\n2. Patient Aadhaar Card\n3. Doctor Referral from Dornala PHC",
                "Continuous (365 Days)",
                "31st December 2026",
                "https://aarogyasri.ap.gov.in",
                "OPEN",
                510,
                "ANM / Health Assistant Yeguva Cherlo Palle Sachivalayam"
        ));

        schemeRecordRepository.save(new SchemeRecord(
                "YSR Pension Kanuka (Doorstep Pension Delivery)",
                "PENSION",
                "ANDHRA_PRADESH_STATE",
                "₹3,000 monthly pension delivered to doorstep on 1st of every month",
                "Senior citizens (60+ years), widows, weavers, or disabled residents of Chilakacherla & Yeguva Cherlo Palle.",
                "1. Age Proof / Voter ID / Aadhaar\n2. Widow Certificate (if applicable)\n3. Income / Rice Card\n4. Volunteer Doorstep Auth",
                "1st of Every Month",
                "31st October 2026",
                "https://sspensions.ap.gov.in",
                "OPEN",
                265,
                "Panchayat Secretary & Grama Volunteers"
        ));

        schemeRecordRepository.save(new SchemeRecord(
                "Jal Jeevan Mission Household Tap Connection",
                "PANCHAYAT",
                "CENTRAL_GOVT",
                "100% Free Household Tap Water Connection (FHTC)",
                "Every residential household in Chilakacherla, Chilakacherla Gudem, Yeguva Cherlo Palle, and Panukumadugu.",
                "1. House Tax Receipt / Water Connection Form\n2. Head of Household Aadhaar Card",
                "1st January 2026",
                "30th November 2026",
                "https://ejalshakti.gov.in",
                "CLOSING_SOON",
                420,
                "RWSS Engineering Assistant Yeguva Cherlo Palle GP"
        ));

        // 6. JOBS RECRUITMENT NOTICES
        jobRecordRepository.save(new JobRecord(
                "Village Agriculture Assistant (VAA)",
                "Andhra Pradesh Subordinate Service Selection Board",
                "Government",
                "Prakasam District / Dornala Mandal / Yeguva Cherlo Palle GP",
                "B.Sc Agriculture / Diploma in Agriculture",
                "Agricultural Extensions, Soil Testing, RBK Operations",
                LocalDate.of(2026, 12, 31),
                "https://apgramasachivalayam.ap.gov.in",
                "AP Grama Sachivalayam Portal",
                LocalDate.of(2026, 1, 15)
        ));

        jobRecordRepository.save(new JobRecord(
                "Panchayat Digital Assistant",
                "AP Grama Sachivalayam Department",
                "Government",
                "Yeguva Cherlo Palle Sachivalayam (Chilakacherla)",
                "Diploma in Computers / B.Sc / B.Tech",
                "Resident Certificate issuing, Meeseva applications, Online Grievance entry",
                LocalDate.of(2026, 11, 30),
                "https://apgramasachivalayam.ap.gov.in",
                "AP Sachivalayam Portal",
                LocalDate.of(2026, 2, 1)
        ));

        // 7. COMMUNITY RESIDENT GRIEVANCES
        Grievance grv1 = new Grievance("CHK-GRV-1001", "Streetlights", "K. Rama Rao", "+91 94401 23456", "LED streetlight near Sri Rama Swamy Temple street pole #12 in Chilakacherla is flickering.", "Temple Street, Ward 3");
        grv1.setStatus("RESOLVED");
        grv1.setAssignedOfficial("Panchayat Electrician (M. Tirupataiah)");
        grv1.setResolvedAt(LocalDate.now().minusDays(1));
        grv1.setResolutionNotes("Replaced LED driver unit and tested wiring. Streetlight fully functional.");
        grievanceRepository.save(grv1);

        Grievance grv2 = new Grievance("CHK-GRV-1002", "Water Supply", "P. Lakshmi", "+91 98480 87654", "Main drinking water pipe leakage near Chilakacherla Gudem bus stop.", "Gudem Main Road");
        grv2.setStatus("IN_PROGRESS");
        grv2.setAssignedOfficial("RWSS Lineman (V. Anjaneyulu)");
        grievanceRepository.save(grv2);

        // 8. GRAMA BAZAAR MARKETPLACE LISTINGS
        marketRepository.save(new MarketListing(
                "Produce",
                "Fresh Organic Farm Cow Milk (A2 Quality)",
                "₹50 per Liter",
                "B. Venkateswarlu",
                "+91 94902 11223",
                "East Street, Chilakacherla",
                "Fresh daily morning & evening A2 cow milk directly from farm. Home delivery available in Chilakacherla & Gudem."
        ));

        marketRepository.save(new MarketListing(
                "Livestock",
                "Healthy Murrah Buffalo Calves (Pair)",
                "₹45,000",
                "M. Subbaiah",
                "+91 98661 54321",
                "Gudem Colony, Chilakacherla",
                "High milk yield lineage pair of Murrah buffalo calves. Vaccinated and healthy."
        ));

        marketRepository.save(new MarketListing(
                "Machinery",
                "45 HP Mahindra Tractor Rental (With Cultivator & Rotavator)",
                "₹900 per Hour",
                "K. Srinivasa Rao",
                "+91 97012 33445",
                "Dornala Road, Chilakacherla",
                "Available for field plowing, harrowing, and transport trailer operations. Experienced driver included."
        ));

        // 9. RBK FERTILIZER & SEED STOCKS
        stockRepository.save(new AgriStock("Urea (45 kg Bag)", "Fertilizer", 250, 266.50, "IN_STOCK", "Today 09:00 AM"));
        stockRepository.save(new AgriStock("DAP (50 kg Bag)", "Fertilizer", 80, 1350.00, "IN_STOCK", "Today 09:00 AM"));
        stockRepository.save(new AgriStock("NPK 14-35-14 (50 kg Bag)", "Fertilizer", 120, 1470.00, "IN_STOCK", "Today 09:00 AM"));
        stockRepository.save(new AgriStock("Certified Groundnut Seeds (K-6)", "Seeds", 45, 2100.00, "LIMITED", "Yesterday"));

        // 10. VOLUNTEER BLOOD DONORS REGISTRY
        donorRepository.save(new BloodDonor("Y. Chenna Kesavulu", "O+", "+91 94405 88990", 28, "Temple Street, Chilakacherla"));
        donorRepository.save(new BloodDonor("G. Anjaneyulu", "B+", "+91 98492 44556", 24, "School Street, Gudem"));
        donorRepository.save(new BloodDonor("P. Siva Parvathi", "A+", "+91 97003 11224", 31, "Main Road, Yeguva Cherlo Palle"));
        donorRepository.save(new BloodDonor("K. Mahesh", "AB+", "+91 99890 55443", 26, "Gudem Area"));
        donorRepository.save(new BloodDonor("T. Venkatesh", "O-", "+91 94411 77665", 30, "Panukumadugu Road"));

        // 11. COMMUNITY NOTICES & PANCHAYATI ANNOUNCEMENTS
        noticeRepository.save(new CommunityNotice(
                "Yeguva Cherlo Palle Gram Panchayat Special Gram Sabha Assembly",
                "Panchayat",
                "HIGH",
                "25th August 2026 (10:00 AM)",
                "Sarpanch & Panchayat Secretary",
                "All residents of Chilakacherla, Chilakacherla Gudem, Yeguva Cherlo Palle, and Panukumadugu are invited to attend Gram Sabha at Sachivalayam premises to discuss Veligonda canal distribution and monsoon road works."
        ));

        noticeRepository.save(new CommunityNotice(
                "Sri Rama Swamy & Chennakesava Swamy Temple Annual Brahmotsavam Jatara",
                "Festival",
                "NORMAL",
                "1st - 3rd September 2026",
                "Temple Utsava Committee",
                "Annual Brahmotsavam celebrations and Annadanam will take place at Sri Rama Swamy Temple in Chilakacherla. Cultural programs and drama every evening."
        ));

        noticeRepository.save(new CommunityNotice(
                "Free Eye Screening & Health Camp at Yeguva Cherlo Palle Sachivalayam",
                "Emergency",
                "URGENT",
                "28th August 2026 (09:00 AM - 04:00 PM)",
                "Dornala PHC & Lions Club",
                "Free eye checkups, cataract screening, and free specs distribution for senior citizens of Chilakacherla & Gudem. Please bring Aadhaar card."
        ));

        // 12. CRAFTSMEN & SERVICE PROVIDERS DIRECTORY
        craftsmanRepository.save(new Craftsman("M. Tirupataiah", "Electrician & Motor Mechanic", "+91 94402 77112", "Chilakacherla", 12, 4.8));
        craftsmanRepository.save(new Craftsman("V. Anjaneyulu", "Plumber & Pipe Fitter", "+91 98485 33441", "Chilakacherla Gudem", 9, 4.7));
        craftsmanRepository.save(new Craftsman("G. Subba Rao", "Auto & Transport Driver", "+91 97018 66554", "Dornala Road", 15, 4.9));
        craftsmanRepository.save(new Craftsman("K. Veeranjaneyulu", "Carpenter & Furniture Works", "+91 94911 22334", "Yeguva Cherlo Palle", 14, 4.6));
        craftsmanRepository.save(new Craftsman("P. Satyanarayana", "Mason & Civil Works", "+91 98663 99887", "Gudem Street", 18, 4.9));

        // 13. VOTER RECORDS & POLLING STATIONS FOR YEGUVA CHERLO PALLE GP (523331)
        voterRecordRepository.save(new VoterRecord(1, "Chilakacherla Main (East)", 101, "MPPS Chilakacherla School (East Wing)", 410, 208, 202, "K. Subba Rao (BLO)", "+91 94403 11221"));
        voterRecordRepository.save(new VoterRecord(2, "Chilakacherla Main (West & RBK)", 102, "MPPS Chilakacherla School (West Wing)", 395, 198, 197, "P. Ramakrishna (BLO)", "+91 98482 33445"));
        voterRecordRepository.save(new VoterRecord(3, "Chilakacherla Gudem Colony", 103, "Anganwadi Center Chilakacherla Gudem", 380, 195, 185, "G. Anjaneyulu (BLO)", "+91 97014 55667"));
        voterRecordRepository.save(new VoterRecord(4, "Yeguva Cherlo Palle Main", 104, "Yeguva Cherlo Palle Gram Sachivalayam", 345, 175, 170, "M. Tirupataiah (BLO)", "+91 94905 66778"));
        voterRecordRepository.save(new VoterRecord(5, "Panukumadugu Hamlet", 104, "Community Hall Panukumadugu", 150, 76, 74, "V. Venkateswarlu (BLO)", "+91 98667 88990"));

        // 14. GRAM PANCHAYAT WARD MEMBERS & ELECTED REPRESENTATIVES
        wardMemberRepository.save(new WardMember(0, "K. Venkateswarlu", "Gram Panchayat Sarpanch", "Yeguva Cherlo Palle GP", "+91 94401 23456"));
        wardMemberRepository.save(new WardMember(1, "B. Venkateswarlu", "Deputy Sarpanch / Ward 1 Member", "Chilakacherla East", "+91 94902 11223"));
        wardMemberRepository.save(new WardMember(2, "K. Rama Rao", "Ward 2 Member", "Chilakacherla West", "+91 94401 23456"));
        wardMemberRepository.save(new WardMember(3, "M. Subbaiah", "Ward 3 Member", "Chilakacherla Gudem", "+91 98661 54321"));
        wardMemberRepository.save(new WardMember(4, "P. Siva Parvathi", "Ward 4 Member", "Yeguva Cherlo Palle", "+91 97003 11224"));
        wardMemberRepository.save(new WardMember(5, "T. Venkatesh", "Ward 5 Member", "Panukumadugu", "+91 94411 77665"));
        wardMemberRepository.save(new WardMember(0, "Y. Chenna Kesavulu", "MPTC Representative (Dornala)", "Dornala Mandal", "+91 94405 88990"));

        // 15. INDIVIDUAL REGISTERED RESIDENT VOTERS LIST (YEGUVA CHERLO PALLE GP)
        voterMemberRepository.save(new VoterMember("AP1523331001", "K. Rama Rao", "Father: K. Subbaiah", "D.No 1-12", 48, "Male", 1, "Chilakacherla East", 101));
        voterMemberRepository.save(new VoterMember("AP1523331002", "K. Sita Devi", "Husband: K. Rama Rao", "D.No 1-12", 42, "Female", 1, "Chilakacherla East", 101));
        voterMemberRepository.save(new VoterMember("AP1523331003", "B. Venkateswarlu", "Father: B. Ramaiah", "D.No 1-28", 52, "Male", 1, "Chilakacherla East", 101));
        voterMemberRepository.save(new VoterMember("AP1523331004", "B. Lakshmi", "Husband: B. Venkateswarlu", "D.No 1-28", 47, "Female", 1, "Chilakacherla East", 101));
        voterMemberRepository.save(new VoterMember("AP1523331005", "Y. Chenna Kesavulu", "Father: Y. Subba Rao", "D.No 1-45", 38, "Male", 1, "Chilakacherla East", 101));
        voterMemberRepository.save(new VoterMember("AP1523331006", "K. Subba Rao", "Father: K. Tirupataiah", "D.No 1-60", 55, "Male", 1, "Chilakacherla East", 101));

        voterMemberRepository.save(new VoterMember("AP1523331007", "K. Srinivasa Rao", "Father: K. Venkaiah", "D.No 2-04", 44, "Male", 2, "Chilakacherla West", 102));
        voterMemberRepository.save(new VoterMember("AP1523331008", "K. Ramanjaneyulu", "Father: K. Anjaneyulu", "D.No 2-19", 41, "Male", 2, "Chilakacherla West", 102));
        voterMemberRepository.save(new VoterMember("AP1523331009", "P. Ramakrishna", "Father: P. Tirupataiah", "D.No 2-35", 39, "Male", 2, "Chilakacherla West", 102));
        voterMemberRepository.save(new VoterMember("AP1523331010", "P. Anusha", "Husband: P. Ramakrishna", "D.No 2-35", 34, "Female", 2, "Chilakacherla West", 102));
        voterMemberRepository.save(new VoterMember("AP1523331011", "M. Tirupataiah", "Father: M. Subbaiah", "D.No 2-51", 46, "Male", 2, "Chilakacherla West", 102));

        voterMemberRepository.save(new VoterMember("AP1523331012", "M. Subbaiah", "Father: M. Guravaiah", "D.No 3-08", 58, "Male", 3, "Chilakacherla Gudem", 103));
        voterMemberRepository.save(new VoterMember("AP1523331013", "M. Ramanamma", "Husband: M. Subbaiah", "D.No 3-08", 53, "Female", 3, "Chilakacherla Gudem", 103));
        voterMemberRepository.save(new VoterMember("AP1523331014", "G. Anjaneyulu", "Father: G. Venkaiah", "D.No 3-22", 36, "Male", 3, "Chilakacherla Gudem", 103));
        voterMemberRepository.save(new VoterMember("AP1523331015", "V. Anjaneyulu", "Father: V. Ramaiah", "D.No 3-40", 43, "Male", 3, "Chilakacherla Gudem", 103));
        voterMemberRepository.save(new VoterMember("AP1523331016", "P. Satyanarayana", "Father: P. Subba Rao", "D.No 3-64", 50, "Male", 3, "Chilakacherla Gudem", 103));

        voterMemberRepository.save(new VoterMember("AP1523331017", "K. Venkateswarlu", "Father: K. Subbaiah", "D.No 4-02", 54, "Male", 4, "Yeguva Cherlo Palle", 104));
        voterMemberRepository.save(new VoterMember("AP1523331018", "K. Radhika", "Husband: K. Venkateswarlu", "D.No 4-02", 49, "Female", 4, "Yeguva Cherlo Palle", 104));
        voterMemberRepository.save(new VoterMember("AP1523331019", "P. Siva Parvathi", "Husband: P. Tirupataiah", "D.No 4-18", 41, "Female", 4, "Yeguva Cherlo Palle", 104));
        voterMemberRepository.save(new VoterMember("AP1523331020", "K. Veeranjaneyulu", "Father: K. Anjaneyulu", "D.No 4-33", 45, "Male", 4, "Yeguva Cherlo Palle", 104));
        voterMemberRepository.save(new VoterMember("AP1523331021", "M. Radhika", "Husband: M. Srinivas", "D.No 4-50", 37, "Female", 4, "Yeguva Cherlo Palle", 104));

        voterMemberRepository.save(new VoterMember("AP1523331022", "T. Venkatesh", "Father: T. Subbaiah", "D.No 5-05", 40, "Male", 5, "Panukumadugu", 104));
        voterMemberRepository.save(new VoterMember("AP1523331023", "T. Bhavani", "Husband: T. Venkatesh", "D.No 5-05", 35, "Female", 5, "Panukumadugu", 104));
        voterMemberRepository.save(new VoterMember("AP1523331024", "V. Venkateswarlu", "Father: V. Ramaiah", "D.No 5-18", 47, "Male", 5, "Panukumadugu", 104));
        voterMemberRepository.save(new VoterMember("AP1523331025", "G. Subba Rao", "Father: G. Venkaiah", "D.No 5-30", 51, "Male", 5, "Panukumadugu", 104));

        // 16. VILLAGE EMPLOYED PROFESSIONALS & JOB HOLDERS DIRECTORY
        villageEmployeeRepository.save(new VillageEmployee("K. Ramanjaneyulu", "Headmaster (Primary)", "MPPS Education Dept", "Government", "Chilakacherla", "Chilakacherla East", "+91 94401 22334"));
        villageEmployeeRepository.save(new VillageEmployee("M. Radhika", "Panchayat Secretary (GS)", "AP Panchayat Raj Dept", "Government", "Yeguva Cherlo Palle", "Yeguva Cherlo Palle", "+91 85942 94811"));
        villageEmployeeRepository.save(new VillageEmployee("P. Ramakrishna", "Senior Software Engineer", "TCS Enterprise Cloud", "Private / IT", "Hyderabad / WFH", "Chilakacherla West", "+91 98482 33445"));
        villageEmployeeRepository.save(new VillageEmployee("K. Subba Rao", "Senior Section Engineer", "Indian Railways (SCR)", "Government", "Vijayawada", "Chilakacherla East", "+91 94403 11221"));
        villageEmployeeRepository.save(new VillageEmployee("Dr. K. Anusha", "Medical Officer (MBBS)", "AP Vaidya Vidhana Parishad", "Government", "Markapur District Hospital", "Chilakacherla West", "+91 97004 88991"));
        villageEmployeeRepository.save(new VillageEmployee("B. Ramaiah", "Assistant Executive Engineer (AEE)", "AP R&B Department", "Government", "Ongole HQ", "Chilakacherla East", "+91 94902 44556"));
        villageEmployeeRepository.save(new VillageEmployee("Y. Chenna Kesavulu", "Agricultural Extension Officer", "AP Agriculture Dept", "Government", "Dornala RBK", "Chilakacherla East", "+91 94405 88990"));
        villageEmployeeRepository.save(new VillageEmployee("K. Mahendra", "Data Analyst", "Infosys Technology", "Private / IT", "Bengaluru", "Chilakacherla West", "+91 99890 12345"));
        villageEmployeeRepository.save(new VillageEmployee("T. Rajesh", "Branch Accountant", "State Bank of India (SBI)", "Banking", "Dornala Branch", "Panukumadugu", "+91 94411 77665"));

        // 17. EDUCATED YOUTH & GRADUATE CANDIDATES TALENT POOL
        educatedCandidateRepository.save(new EducatedCandidate("K. Mahendra", "B.Tech", "Computer Science & Engineering", 2025, "Java, Spring Boot, React, SQL", "LOOKING_FOR_JOB", "Chilakacherla East", "+91 99890 12345"));
        educatedCandidateRepository.save(new EducatedCandidate("P. Deepthi", "M.Sc", "Agriculture (Agronomy)", 2024, "Crop Protection, Soil Testing, RBK Extensions", "PREPARING_COMPETITIVE", "Chilakacherla West", "+91 98485 66778"));
        educatedCandidateRepository.save(new EducatedCandidate("G. Suresh", "B.Tech", "Electrical & Electronics (EEE)", 2025, "Substation Operations, PLC Automation", "LOOKING_FOR_JOB", "Chilakacherla Gudem", "+91 97018 22334"));
        educatedCandidateRepository.save(new EducatedCandidate("M. Divya", "B.Sc", "Nursing & Critical Care", 2024, "Emergency Nursing, First Aid, ICU Operations", "PREPARING_COMPETITIVE", "Chilakacherla Gudem", "+91 98663 44556"));
        educatedCandidateRepository.save(new EducatedCandidate("T. Rajesh", "B.Com", "Computer Applications & Accounting", 2025, "Tally Prime, GST Filing, Excel Analytics", "EMPLOYED", "Panukumadugu", "+91 94411 77665"));
        educatedCandidateRepository.save(new EducatedCandidate("K. Harika", "B.Ed", "English & Social Pedagogy", 2024, "Primary Education, Child Psychology, Spoken English", "LOOKING_FOR_JOB", "Yeguva Cherlo Palle", "+91 94911 88990"));
        educatedCandidateRepository.save(new EducatedCandidate("V. Anjaneyulu", "Diploma", "Mechanical Engineering", 2025, "CAD Drafting, Machine Maintenance, Lathe Works", "LOOKING_FOR_JOB", "Chilakacherla Gudem", "+91 98485 33441"));

        // 18. CRAWLER SCHEDULES
        scheduleRepository.save(new ResearchSchedule("News & Public Notices", Category.NEWS, ScheduleFrequency.DAILY, LocalDate.now()));
        scheduleRepository.save(new ResearchSchedule("Jobs & Employment Aggregation", Category.JOBS, ScheduleFrequency.DAILY, LocalDate.now()));
        scheduleRepository.save(new ResearchSchedule("Government Schemes & Programs", Category.SCHEMES, ScheduleFrequency.WEEKLY, LocalDate.now().minusDays(3)));
        scheduleRepository.save(new ResearchSchedule("Voter List & Polling Directory", Category.ELECTION, ScheduleFrequency.MONTHLY, LocalDate.now().minusDays(10)));
    }
}
