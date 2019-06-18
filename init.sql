DROP SCHEMA IF EXISTS pae CASCADE;
CREATE SCHEMA pae;



-------------CREATE TABLE-------------

-------TABLE COUNTRIES-------

CREATE TABLE pae.countries (
    country_name VARCHAR(150) NOT NULL,
    country_code3 CHAR(3) PRIMARY KEY
);



-------TABLE USERS-------

CREATE TABLE pae.users (
    id_user SERIAL PRIMARY KEY,
    last_name VARCHAR(150) NOT NULL,
    first_name VARCHAR(150) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password_user VARCHAR(250) NOT NULL,
    username VARCHAR(150) NOT NULL,
    type_user CHAR NOT NULL,
    registration_date DATE NOT NULL,
    nationality CHAR(3) NOT NULL REFERENCES pae.countries(country_code_3),
    title VARCHAR(10),
    birthdate DATE,
    address_user VARCHAR(100),
    postcode VARCHAR(50),
    town VARCHAR(100),
    telephone INTEGER,
    sex CHAR,
    succeeded_years INTEGER,
    iban VARCHAR(32),
    bank_holder VARCHAR(100),
    bic_code VARCHAR(20),
    bank_name VARCHAR(150)
);



-------TABLE ORGANISATIONS-------

CREATE TABLE pae.organisations (
    id_organisation SERIAL PRIMARY KEY,
    legal_name VARCHAR(150) NOT NULL,
    business_name VARCHAR(150) NOT NULL,
    full_legal_name VARCHAR(150) NOT NULL,
    departement VARCHAR(100),
    type_organisation CHAR(3),
    number_of_employees INTEGER,
    email VARCHAR(100),
    web_site VARCHAR(100),
    legal_address VARCHAR(100) NOT NULL,
    country CHAR(3) NOT NULL REFERENCES pae.countries(country_code_3),
    region VARCHAR(100),
    city VARCHAR(100) NOT NULL,
    po_box VARCHAR(10),
    post_code VARCHAR(50) NOT NULL,
    cedex VARCHAR(50),
    telephone1 VARCHAR(100) NOT NULL,
    telephone2 INTEGER,
    fax VARCHAR(100),
    comment_orga VARCHAR(500),
    public_body BOOLEAN,
    non_profit BOOLEAN,
    organisation CHAR NOT NULL
);



-------TABLE MOBILITY_CHOICES-------

CREATE TABLE pae.mobility_choices (
    id_mobility_choices SERIAL PRIMARY KEY,
    preference_order INTEGER,
    programme CHAR(2) NOT NULL,
    country CHAR(3) NOT NULL REFERENCES pae.countries(country_code_3),
    academic_year INTEGER NOT NULL,
    semester CHAR(2) NOT NULL,
    id_user INTEGER NOT NULL REFERENCES pae.users(id_user),
    partner INTEGER NOT NULL REFERENCES pae.organisations(id_organisation),
    mobility_type CHAR(3) NOT NULL
);



-------TABLE MOBILITIES-------

CREATE TABLE pae.mobilities (
    id_mobility_choices INTEGER NOT NULL REFERENCES pae.mobility_choices(id_mobility_choices),
    PRIMARY KEY (id_mobility_choices),
    state_mobility CHAR(2) NOT NULL,
    mobility_confirmation CHAR NOT NULL,
    pro_eco BOOLEAN,
    other_tools VARCHAR(2)
);



-------TABLE CANCELLATIONS-------

CREATE TABLE pae.cancellations (
    id_mobility_choices INTEGER NOT NULL REFERENCES pae.mobilities(id_mobility_choices),
    PRIMARY KEY (id_mobility_choices),
    reason VARCHAR(500) NOT NULL,
    date_cancellation DATE NOT NULL,
    id_user INTEGER NOT NULL REFERENCES pae.users(id_user)
);



-------TABLE MOBILITY_DOCUMENTS-------

CREATE TABLE pae.mobility_documents (
    id_mobility_choices INTEGER NOT NULL REFERENCES pae.mobilities(id_mobility_choices),
    doc VARCHAR(3) NOT NULL,
    PRIMARY KEY (id_mobility_choices, doc),
    signed BOOLEAN
);



---------------------------------------

INSERT INTO pae.countries VALUES ('Afghanistan','AFG');
INSERT INTO pae.countries VALUES ('Afrique du Sud', 'ZAF');
INSERT INTO pae.countries VALUES ('Albanie', 'ALB');
INSERT INTO pae.countries VALUES ('Algerie', 'DZA');
INSERT INTO pae.countries VALUES ('Allemagne', 'DEU');
INSERT INTO pae.countries VALUES ('Andorre', 'AND');
INSERT INTO pae.countries VALUES ('Angola', 'AGO');
INSERT INTO pae.countries VALUES ('Anguilla', 'AIA');
INSERT INTO pae.countries VALUES ('Antarctique', 'ATA');
INSERT INTO pae.countries VALUES ('Antigua et Barbuda', 'ATG');
INSERT INTO pae.countries VALUES ('Antilles Néerlandaises', 'ANT');
INSERT INTO pae.countries VALUES ('Arabie Saoudite', 'SAU');
INSERT INTO pae.countries VALUES ('Argentine', 'ARG');
INSERT INTO pae.countries VALUES ('Arménie', 'ARM');
INSERT INTO pae.countries VALUES ('Aruba', 'ABW');
INSERT INTO pae.countries VALUES ('Australie', 'AUS');
INSERT INTO pae.countries VALUES ('Autriche', 'AUT');
INSERT INTO pae.countries VALUES ('Azerbaidjan', 'AZE');
INSERT INTO pae.countries VALUES ('Bahamas', 'BHS');
INSERT INTO pae.countries VALUES ('Bahrein', 'BHR');
INSERT INTO pae.countries VALUES ('Bangladesh', 'BGD');
INSERT INTO pae.countries VALUES ('Barbade', 'BRB');
INSERT INTO pae.countries VALUES ('Bélarus', 'BLR');
INSERT INTO pae.countries VALUES ('Belgique', 'BEL');
INSERT INTO pae.countries VALUES ('Belize', 'BLZ');
INSERT INTO pae.countries VALUES ('Bénin', 'BEN');
INSERT INTO pae.countries VALUES ('Bermudes', 'BMU');
INSERT INTO pae.countries VALUES ('Bhoutan', 'BTN');
INSERT INTO pae.countries VALUES ('Bolivie', 'BOL');
INSERT INTO pae.countries VALUES ('Bosnie et Herzégovine', 'BIH');
INSERT INTO pae.countries VALUES ('Botswana', 'BWA');
INSERT INTO pae.countries VALUES ('Bouvet Island', 'BVT');
INSERT INTO pae.countries VALUES ('Brésil', 'BRA');
INSERT INTO pae.countries VALUES ('Brunei', 'BRN');
INSERT INTO pae.countries VALUES ('Bulgarie', 'BGR');
INSERT INTO pae.countries VALUES ('Burkina Fasco', 'BFA');
INSERT INTO pae.countries VALUES ('Burundi', 'BDI');
INSERT INTO pae.countries VALUES ('Cambodge', 'KHM');
INSERT INTO pae.countries VALUES ('Cameroun', 'CMR');
INSERT INTO pae.countries VALUES ('Canada', 'CAN');
INSERT INTO pae.countries VALUES ('Cap Vert', 'CPV');
INSERT INTO pae.countries VALUES ('Chili', 'CHL');
INSERT INTO pae.countries VALUES ('Chine', 'CHN');
INSERT INTO pae.countries VALUES ('Chypre', 'CYP');
INSERT INTO pae.countries VALUES ('Cité du Vatican', 'VAT');
INSERT INTO pae.countries VALUES ('Colombie', 'COL');
INSERT INTO pae.countries VALUES ('Comores', 'COM');
INSERT INTO pae.countries VALUES ('Congo, République', 'COG');
INSERT INTO pae.countries VALUES ('Congo, République Démocratique du', 'COD');
INSERT INTO pae.countries VALUES ('Corée du Nord', 'PRK');
INSERT INTO pae.countries VALUES ('Corée du Sud', 'KOR');
INSERT INTO pae.countries VALUES ('Costa Rica', 'CRI');
INSERT INTO pae.countries VALUES ('Côte d"Ivoire', 'CIV');
INSERT INTO pae.countries VALUES ('Croatie', 'HRV');
INSERT INTO pae.countries VALUES ('Cuba', 'CUB');
INSERT INTO pae.countries VALUES ('Curacao', 'CUW');
INSERT INTO pae.countries VALUES ('Danemark', 'DNK');
INSERT INTO pae.countries VALUES ('Djibouti',  'DJI');
INSERT INTO pae.countries VALUES ('Dominique', 'DMA');
INSERT INTO pae.countries VALUES ('Egypte', 'EGY');
INSERT INTO pae.countries VALUES ('Emirats Arabes Unis', 'ARE');
INSERT INTO pae.countries VALUES ('Equateur', 'ECU');
INSERT INTO pae.countries VALUES ('Erythrée', 'ERI');
INSERT INTO pae.countries VALUES ('Espagne', 'ESP');
INSERT INTO pae.countries VALUES ('Estonie', 'EST');
INSERT INTO pae.countries VALUES ('Etats-Unis', 'USA');
INSERT INTO pae.countries VALUES ('Ethiopie', 'ETH');
INSERT INTO pae.countries VALUES ('Fidji', 'FJI');
INSERT INTO pae.countries VALUES ('Finlande', 'FIN');
INSERT INTO pae.countries VALUES ('France', 'FRA');
INSERT INTO pae.countries VALUES ('France, Métropolitaine', 'FXX');
INSERT INTO pae.countries VALUES ('Gabon', 'GAB');
INSERT INTO pae.countries VALUES ('Gambie', 'GMB');
INSERT INTO pae.countries VALUES ('Gaza', 'PSE');
INSERT INTO pae.countries VALUES ('Géorgie', 'GEO');
INSERT INTO pae.countries VALUES ('Géorgie du Sud et les îles Sandwich du Sud', 'SGS');
INSERT INTO pae.countries VALUES ('Ghana', 'GHA');
INSERT INTO pae.countries VALUES ('Gibraltar', 'GIB');
INSERT INTO pae.countries VALUES ('Grèce', 'GRC');
INSERT INTO pae.countries VALUES ('Grenade', 'GRD');
INSERT INTO pae.countries VALUES ('Greoenland', 'GRL');
INSERT INTO pae.countries VALUES ('Guadeloupe', 'GLP');
INSERT INTO pae.countries VALUES ('Guam', 'GUM');
INSERT INTO pae.countries VALUES ('Guatemala', 'GTM');
INSERT INTO pae.countries VALUES ('Guinée', 'GIN');
INSERT INTO pae.countries VALUES ('Guinée Bissau', 'GNB');
INSERT INTO pae.countries VALUES ('Guinée Equatoriale', 'GNQ');
INSERT INTO pae.countries VALUES ('Guyane', 'GUY');
INSERT INTO pae.countries VALUES ('Guyane Française', 'GUF');
INSERT INTO pae.countries VALUES ('Haïti', 'HTI');
INSERT INTO pae.countries VALUES ('Honduras', 'HND');
INSERT INTO pae.countries VALUES ('Hong Kong', 'HKG');
INSERT INTO pae.countries VALUES ('Hongrie', 'HUN');
INSERT INTO pae.countries VALUES ('Ile de Man', 'IMN');
INSERT INTO pae.countries VALUES ('Iles Caïman', 'CYM');
INSERT INTO pae.countries VALUES ('Iles Christmas', 'CXR');
INSERT INTO pae.countries VALUES ('Iles Cocos', 'CCK');
INSERT INTO pae.countries VALUES ('Iles Cook', 'COK');
INSERT INTO pae.countries VALUES ('Iles Féroé', 'FRO');
INSERT INTO pae.countries VALUES ('Iles Guernesey', 'GGY');
INSERT INTO pae.countries VALUES ('Iles Heardet McDonald', 'HMD');
INSERT INTO pae.countries VALUES ('Iles Malouines', 'FLK');
INSERT INTO pae.countries VALUES ('Iles Mariannes du Nord', 'MNP');
INSERT INTO pae.countries VALUES ('Iles Marshall', 'MHL');
INSERT INTO pae.countries VALUES ('Iles Maurice', 'MUS');
INSERT INTO pae.countries VALUES ('Iles mineures éloignées des Etats-Unis', 'UMI');
INSERT INTO pae.countries VALUES ('Iles Norfolk', 'NFK');
INSERT INTO pae.countries VALUES ('Iles Salomon', 'SLB');
INSERT INTO pae.countries VALUES ('Iles Turques et Caïque', 'TCA');
INSERT INTO pae.countries VALUES ('Iles Vierges des Etats-Unis', 'VIR');
INSERT INTO pae.countries VALUES ('Iles Virges du Royaume-Uni', 'VGB');
INSERT INTO pae.countries VALUES ('Inde', 'IND');
INSERT INTO pae.countries VALUES ('Indonésie', 'IDN');
INSERT INTO pae.countries VALUES ('Iran', 'IRN');
INSERT INTO pae.countries VALUES ('Iraq', 'IRQ');
INSERT INTO pae.countries VALUES ('Irlande', 'IRL');
INSERT INTO pae.countries VALUES ('Islande', 'ISL');
INSERT INTO pae.countries VALUES ('Israël', 'ISR');
INSERT INTO pae.countries VALUES ('Italie', 'ITA');
INSERT INTO pae.countries VALUES ('Jamaique', 'JAM');
INSERT INTO pae.countries VALUES ('Japon', 'JPN');
INSERT INTO pae.countries VALUES ('Jersey', 'JEY');
INSERT INTO pae.countries VALUES ('Jordanie', 'JOR');
INSERT INTO pae.countries VALUES ('Kazakhstan', 'KAZ');
INSERT INTO pae.countries VALUES ('Kenya', 'KEN');
INSERT INTO pae.countries VALUES ('Kirghizistan', 'KGZ');
INSERT INTO pae.countries VALUES ('Kiribati', 'KIR');
INSERT INTO pae.countries VALUES ('Kosovo', 'XKO');
INSERT INTO pae.countries VALUES ('Koweit', 'KWT');
INSERT INTO pae.countries VALUES ('Laos', 'LAO');
INSERT INTO pae.countries VALUES ('Lesotho', 'LSO');
INSERT INTO pae.countries VALUES ('Lettonie', 'LVA');
INSERT INTO pae.countries VALUES ('Liban', 'LBN');
INSERT INTO pae.countries VALUES ('Libéria', 'LBR');
INSERT INTO pae.countries VALUES ('Libye', 'LBY');
INSERT INTO pae.countries VALUES ('Liechtenstein', 'LIE');
INSERT INTO pae.countries VALUES ('Lituanie', 'LTU');
INSERT INTO pae.countries VALUES ('Luxembourg', 'LUX');
INSERT INTO pae.countries VALUES ('Macao', 'MAC');
INSERT INTO pae.countries VALUES ('Macédoine', 'MKD');
INSERT INTO pae.countries VALUES ('Madagascar', 'MDG');
INSERT INTO pae.countries VALUES ('Malaisie', 'MYS');
INSERT INTO pae.countries VALUES ('Malawi', 'MWI');
INSERT INTO pae.countries VALUES ('Maldives', 'MDV');
INSERT INTO pae.countries VALUES ('Mali', 'MLI');
INSERT INTO pae.countries VALUES ('Malte', 'MLT');
INSERT INTO pae.countries VALUES ('Maroc', 'MAR');
INSERT INTO pae.countries VALUES ('Martinique', 'MTQ');
INSERT INTO pae.countries VALUES ('Mauritanie', 'MRT');
INSERT INTO pae.countries VALUES ('Mayotte', 'MYT');
INSERT INTO pae.countries VALUES ('Mexique', 'MEX');
INSERT INTO pae.countries VALUES ('Micronésie', 'FSM');
INSERT INTO pae.countries VALUES ('Moldavie', 'MDA');
INSERT INTO pae.countries VALUES ('Monaco', 'MCO');
INSERT INTO pae.countries VALUES ('Mongolie', 'MNG');
INSERT INTO pae.countries VALUES ('Monténégro', 'MNE');
INSERT INTO pae.countries VALUES ('Montserrat', 'MSR');
INSERT INTO pae.countries VALUES ('Mozambique', 'MOZ');
INSERT INTO pae.countries VALUES ('Myanmar', 'MMR');
INSERT INTO pae.countries VALUES ('Namibie', 'NAM');
INSERT INTO pae.countries VALUES ('Nauru', 'NRU');
INSERT INTO pae.countries VALUES ('Népal', 'NPL');
INSERT INTO pae.countries VALUES ('Nicaragua', 'NIC');
INSERT INTO pae.countries VALUES ('Niger', 'NER');
INSERT INTO pae.countries VALUES ('Nigeria', 'NGA');
INSERT INTO pae.countries VALUES ('Niue', 'NIU');
INSERT INTO pae.countries VALUES ('Norvège', 'NOR');
INSERT INTO pae.countries VALUES ('Nouvelle Calédonie', 'NCL');
INSERT INTO pae.countries VALUES ('Nouvelle Zélande', 'NZL');
INSERT INTO pae.countries VALUES ('Oman', 'OMN');
INSERT INTO pae.countries VALUES ('Ouganda', 'UGA');
INSERT INTO pae.countries VALUES ('Ouzbékistan', 'UZB');
INSERT INTO pae.countries VALUES ('Pakistan', 'PAK');
INSERT INTO pae.countries VALUES ('Palau', 'PLW');
INSERT INTO pae.countries VALUES ('Panama', 'PAN');
INSERT INTO pae.countries VALUES ('Papouasie Nouvelle Guinée', 'PNG');
INSERT INTO pae.countries VALUES ('Paraguay', 'PRY');
INSERT INTO pae.countries VALUES ('Pays-Bas', 'NLD');
INSERT INTO pae.countries VALUES ('Pérou', 'PER');
INSERT INTO pae.countries VALUES ('Philippines', 'PHL');
INSERT INTO pae.countries VALUES ('Pitcairn', 'PCN');
INSERT INTO pae.countries VALUES ('Pologne', 'POL');
INSERT INTO pae.countries VALUES ('Polynésie Française', 'PYF');
INSERT INTO pae.countries VALUES ('Porto Rico', 'PRI');
INSERT INTO pae.countries VALUES ('Portugal', 'PRT');
INSERT INTO pae.countries VALUES ('Qatar', 'QAT');
INSERT INTO pae.countries VALUES ('République Centraficaine', 'CAF');
INSERT INTO pae.countries VALUES ('République Dominicaine', 'DOM');
INSERT INTO pae.countries VALUES ('République Tchèque', 'CZE');
INSERT INTO pae.countries VALUES ('Réunion', 'REU');
INSERT INTO pae.countries VALUES ('Roumanie', 'ROU');
INSERT INTO pae.countries VALUES ('Royaume Uni', 'GBR');
INSERT INTO pae.countries VALUES ('Russie', 'RUS');
INSERT INTO pae.countries VALUES ('Rwanda', 'RWA');
INSERT INTO pae.countries VALUES ('Sahara Occidental', 'ESH');
INSERT INTO pae.countries VALUES ('Saint Barthelemy', 'BLM');
INSERT INTO pae.countries VALUES ('Saint Hélène', 'SHN');
INSERT INTO pae.countries VALUES ('Saint Kitts et Nevis', 'KNA');
INSERT INTO pae.countries VALUES ('Saint Martin', 'MAF');
INSERT INTO pae.countries VALUES ('Saint Martin', 'SXM');
INSERT INTO pae.countries VALUES ('Saint Pierre et Miquelon', 'SPM');
INSERT INTO pae.countries VALUES ('Saint Vincent et les Grenadines', 'VCT');
INSERT INTO pae.countries VALUES ('Sainte Lucie', 'LCA');
INSERT INTO pae.countries VALUES ('Salvador', 'SLV');
INSERT INTO pae.countries VALUES ('Samoa Americaines', 'ASM');
INSERT INTO pae.countries VALUES ('Samoa Occidentales', 'WSM');
INSERT INTO pae.countries VALUES ('San Marin', 'SMR');
INSERT INTO pae.countries VALUES ('Sao Tomé et Principe', 'STP');
INSERT INTO pae.countries VALUES ('Sénégal', 'SEN');
INSERT INTO pae.countries VALUES ('Serbie', 'SRB');
INSERT INTO pae.countries VALUES ('Seychelles', 'SYC');
INSERT INTO pae.countries VALUES ('Sierra Léone', 'SLE');
INSERT INTO pae.countries VALUES ('Singapour', 'SGP');
INSERT INTO pae.countries VALUES ('Slovaquie', 'SVK');
INSERT INTO pae.countries VALUES ('Slovénie', 'SVN');
INSERT INTO pae.countries VALUES ('Somalie', 'SOM');
INSERT INTO pae.countries VALUES ('Soudan', 'SDN');
INSERT INTO pae.countries VALUES ('Sri Lanka', 'LKA');
INSERT INTO pae.countries VALUES ('Sud Soudan', 'SSD');
INSERT INTO pae.countries VALUES ('Suède', 'SWE');
INSERT INTO pae.countries VALUES ('Suisse', 'CHE');
INSERT INTO pae.countries VALUES ('Surinam', 'SUR');
INSERT INTO pae.countries VALUES ('Svalbard et Jan Mayen', 'SJM');
INSERT INTO pae.countries VALUES ('Swaziland', 'SWZ');
INSERT INTO pae.countries VALUES ('Syrie', 'SYR');
INSERT INTO pae.countries VALUES ('Tadjikistan', 'TJK');
INSERT INTO pae.countries VALUES ('Taiwan', 'TWN');
INSERT INTO pae.countries VALUES ('Tanzanie', 'TZA');
INSERT INTO pae.countries VALUES ('Tchad', 'TCD');
INSERT INTO pae.countries VALUES ('Terres Australes et Antarctique Françaises', 'ATF');
INSERT INTO pae.countries VALUES ('Térritoire Britannique de l"Océan Indien', 'IOT');
--INSERT INTO pae.countries VALUES ('Territoires Palestiniens occupés', 'PSE');
INSERT INTO pae.countries VALUES ('Thaïlande', 'THA');
INSERT INTO pae.countries VALUES ('Timor-Leste', 'TLS');
INSERT INTO pae.countries VALUES ('Togo', 'TGO');
INSERT INTO pae.countries VALUES ('Tokelau', 'TKL');
INSERT INTO pae.countries VALUES ('Tonga', 'TON');
INSERT INTO pae.countries VALUES ('Trinité et Tobago', 'TTO');
INSERT INTO pae.countries VALUES ('Tunisie', 'TUN');
INSERT INTO pae.countries VALUES ('Turkmenistan', 'TKM');
INSERT INTO pae.countries VALUES ('Turquie', 'TUR');
INSERT INTO pae.countries VALUES ('Tuvalu', 'TUV');
INSERT INTO pae.countries VALUES ('Ukraine', 'UKR');
INSERT INTO pae.countries VALUES ('Uruguay', 'URY');
INSERT INTO pae.countries VALUES ('Vanuatu', 'VUT');
INSERT INTO pae.countries VALUES ('Venezuela', 'VEN');
INSERT INTO pae.countries VALUES ('Vietnam', 'VNM');
INSERT INTO pae.countries VALUES ('Wallis et Futuna', 'WLF');
INSERT INTO pae.countries VALUES ('Yemen', 'YEM');
INSERT INTO pae.countries VALUES ('Zambie', 'ZMB');
INSERT INTO pae.countries VALUES ('Zimbabwe', 'ZWE');




INSERT INTO pae.users VALUES (DEFAULT, 'Sako', 'Jozef', 'jozef.sako@student.vinci.be', '$2y$10$zJDYbZijwZGbYr4agA7DsuPHJZyjI6J9lptWWWE7OOf7tX5IdyXxC', 'jozef_sako', 'S', '2019-02-24', 'BEL'); --mdpjozef
INSERT INTO pae.users VALUES (DEFAULT, 'Jacob', 'Loury', 'loury.jacob@student.vinci.be', '$2y$10$U4C504lfMavuzM0taMS29u462Jah1ousaeVoa4jWIMYFnyDu7/d0a', 'loury_jacob', 'S', '2019-02-24', 'BEL'); --mdploury
INSERT INTO pae.users VALUES (DEFAULT, 'De Meeus', 'Augustin', 'augustin.demeeus@student.vinci.be', '$2y$10$FxEauDfdiE4U2655aXRjp.nqU.DB6SOmyeJe.VbI1KwxoRErnyKQ6', 'augustin_demeeus', 'S', '2019-02-24', 'BEL'); --mdpaugustin
INSERT INTO pae.users VALUES (DEFAULT, 'De Pape', 'Alexandre', 'alexandre.depape@student.vinci.be', '$2y$10$DTeswOGZCkN28yGOKqbOW.nAWOurzfUxkUaW3xh/M/gmYWHB5eEfG', 'alexandre_depape', 'S', '2019-02-24', 'BEL'); --mdpalexandre
INSERT INTO pae.users VALUES (DEFAULT, 'Schellens', 'Valentin', 'valentin.schellens@student.vinci.be', '$2y$10$Hr8x52pk1jdHBHTOHuVEw.M6W0XUFPKTUdXnHteTxtXZcLGDZf6y.', 'valentin_schellens', 'S', '2019-02-24', 'BEL'); --mdpvalentin

INSERT INTO pae.organisations VALUES (DEFAULT, 'UC Leuven-Limbourg (Leuven campus)', 'UC Leuven-Limbourg (Leuven campus)', 'UC Leuven-Limbourg (Leuven campus)', 'Computer Sciences', 'SMS', NULL, 'international@ucll.be', 'https://www.ucll.be/', 'Abdij van Park 9', 'BEL', NULL, 'Heverlee', NULL, '3001', NULL, '+32 (0)16 375 735', NULL, NULL, NULL, NULL, NULL, 'S');
INSERT INTO pae.organisations VALUES (DEFAULT, 'Technological University Dublin', 'Technological University Dublin', 'Technological University Dublin', 'Computing', 'SMS', NULL, 'erasmus@dit.ie', 'www.dit.ie/computing/', '40-45 Mount Joy Square', 'IRL', NULL, 'Dublin', NULL, 'Dublin 1', NULL, '+35314023404', NULL, NULL, NULL, NULL, NULL, 'S');
INSERT INTO pae.organisations VALUES (DEFAULT, 'Université du Luxembourg', 'Université du Luxembourg', 'Université du Luxembourg', 'Computing', 'SMS', NULL, 'erasmus@uni.lu', 'https://wwwfr.uni.lu/', '7, avenue des Hauts-Fourneaux', 'LUX', NULL, 'Esch-sur-Alzette', NULL, 'L-4362', NULL, '(+352) 46 66 44 4000', NULL, NULL, NULL, NULL, NULL, 'S');
INSERT INTO pae.organisations VALUES (DEFAULT, 'HES-SO Haute école spécialisée de Suisse occidentale', 'HES-SO Haute école spécialisée de Suisse occidentale (Haute école de gestion de Genève (HEG GE))', 'HES-SO Haute école spécialisée de Suisse occidentale (Haute école de gestion de Genève (HEG GE))', 'Business Information Systems', 'SMS', NULL, 'international@hes-so.ch', NULL, '17, Rue de la Tambourine', 'CHE', NULL, 'Carouge-Genève', NULL, '1227', NULL, '0041 22 388 17 00', NULL, NULL, NULL, NULL, NULL, 'S');
INSERT INTO pae.organisations VALUES (DEFAULT, 'cégep Edouard Montpetit', 'cégep Edouard Montpetit', 'cégep Edouard Montpetit', 'Techniques de l"informatique', 'SMS', NULL, NULL, 'http://www.cegepmonpetit.ca/', '945 chemin de Chambly', 'CAN', 'Québec', 'Longueuil', NULL, 'J4H 3M6', NULL, '450 679-2631', NULL, NULL, NULL, NULL, NULL, 'S');
INSERT INTO pae.organisations VALUES (DEFAULT, 'Collège de Maisonneuve', 'Collège de Maisonneuve', 'Collège de Maisonneuve', 'Techniques de l"informatique', 'SMS', NULL, NULL, 'https://www.cmaisonneuve.qc.ca/ ', '3800, rue Sherbrooke Est', 'CAN', 'Québec', 'Montréal', NULL, 'H1X 2A2', NULL, '514 254-7131', NULL, NULL, NULL, NULL, NULL, 'S');
INSERT INTO pae.organisations VALUES (DEFAULT, 'Cégep de Chicoutimi', 'Cégep de Chicoutimi', 'Cégep de Chicoutimi', 'Techniques de l"informatique', 'SMS', NULL, 'mhforest@cchic.ca', 'https://cchic.ca', '534 Jacques-Cartier Est', 'CAN', 'Québec', 'Chicoutimi', NULL, 'G7H 1Z6', NULL, '418 549.9520', NULL, NULL, NULL, NULL, NULL, 'S');
INSERT INTO pae.organisations VALUES (DEFAULT, 'Wölfel Engineering GmbH', 'Wölfel Engineering GmbH', 'Wölfel Engineering GmbH', 'Data processing and analytics', 'SMP', NULL, 'tr@woelfel.de', 'https://www.woelfel.de/home.html', 'Max-Planck-Str. 15','DEU', NULL, 'Höchberg', NULL, '97204', NULL, '+49 (931) 49708-168', NULL, NULL, NULL, NULL, NULL, 'O');






















































