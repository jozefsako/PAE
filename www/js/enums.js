let ViewHome = {
  REGISTER: "#form_register",
  LOGIN: "#form_login",
  ALL: "all",
};

let ViewUser = {
  USER: "#form_user",
  HOME: "home",
  ADD_MOBILITY_CHOICE: "#div_add_new_mobility_choice",
  MOBILITY_CHOICES: "#div_mobility_choices",
  MOBILITES: "#div_mobilites",
  MOBILITY_INFO: "#div_mobility_information",
  MOBILITY_DOC: "#div_docs",
  MOBILITY_CANCELATION : "#div_mobility_cancelation",
  ENCODE_DATA: "#div_encode_data",
  INFOUSER: "#div_users",
  ADD_PARTNERS: "#div_add_partners",
  MOBILITY_SEARCH: "#div_research_mobilities",
  PARTNER_SEARCH: "#div_research_partners",
  USER_SEARCH: "#div_research_users",
  PARTNERS: "#div_partners",
  USER_STATUS : "#div_update_statuts_user",
  ALL: "all",
}

let EnumHref = {
  USERS : "#href_users",
}

let EnumMobilityConfirmation = {
  "Teacher": "T",
  "Student": "S",
  "Reponsible" : "R",
  properties: {
    "T": {name: "Professeur"},
    "S": {name: "Etudiant"},
    "R": {name: "Responsable"},
  }
}

let EnumMobilityProgram = {
  "ERABEL": "EB",
  "ERASMUS+": "ER",
  "FAME": "FA",
  properties: {
    "EB": {name: "Erabel"},
    "ER": {name: "Erasmus+"},
    "FA": {name: "Fame"}
  }
}

let EnumStateMobility = {
  "Created": "cr",
  "InPreparation": "ipp",
  "ToPay": "tp",
  "InProgress": "ip",
  "ToPaySold": "tps",
  "Completed": "co",
  properties: {
    "cr": {EN: "Created", FR: "Créée"},
    "ipp": {EN: "In preparation", FR: "En préparation"},
    "tp": {EN: "To pay", FR: "A payer"},
    "ip": {EN: "In progress", FR: "En cours"},
    "tps": {EN: "To pay Sold", FR: "Solde à payer"},
    "co": {EN: "Completed", FR: "Complétée"},
  }
}

let EnumTypeOrganisation = {
  "S": "School",
  "O": "Organisation",
  "SMS": "School",
  "SMP": "Organisation",
  properties: {
    "S": {name: "Ecole"},
    "O": {name: "Entreprise"},
    "SMS": {name: "Ecole"},
    "SMP": {name: "Organisation"},
  }
}

let EnumDocuments = {
  "SS": "SCHOLARSHIP",
  "ASI": "INTERNSHIP_OR_SCHOOL_AGREEMENT",
  "SCH": "STUDENT_CHARTER",
  "LTD": "LANGUAGE_TEST_DEPARTURE",
  "CD": "COMMITMENT_DOCS",
  "CR": "CERTIFICATE_OF_RESIDENCE",
  "CI": "CERTIFICATE_OF_INTERNSHIP",
  "TN": "TRANSCRIPT_OF_NOTES",
  "FR": "FINAL_REPORT",
  "LTR": "LANGUAGE_TEST_RETURN",
  properties: {
    "SCHOLARSHIP": {EN: "Scholarship", FR: "Bourse d'étude", TIME: "D"},
    "INTERNSHIP_OR_SCHOOL_AGREEMENT": {
      EN: "Internship / School agreement",
      FR: "Convention de Stage/Etudes", TIME: "D"
    },
    "STUDENT_CHARTER": {
      EN: "Student Charter",
      FR: "Charte de l'étudiant",
      TIME: "D"
    },
    "LANGUAGE_TEST_DEPARTURE": {
      EN: "Language Test Departure",
      FR: "Test linguistique de départ",
      TIME: "D"
    },
    "COMMITMENT_DOCS": {
      EN: "Commitment docs",
      FR: "Documents d'engagement",
      TIME: "D"
    },
    "CERTIFICATE_OF_INTERNSHIP": {
      EN: "Certificate of Internship",
      FR: "Certificat de stage",
      TIME: "R"
    },
    "CERTIFICATE_OF_RESIDENCE": {
      EN: "Certificate of Residence",
      FR: "Attestation de séjour",
      TIME: "R"
    },
    "TRANSCRIPT_OF_NOTES": {
      EN: "Transcript of Notes",
      FR: "Releve de notes",
      TIME: "R"
    },
    "FINAL_REPORT": {
      EN: "Final Report",
      FR: "Rapport final completé",
      TIME: "R"
    },
    "LANGUAGE_TEST_RETURN": {
      EN: "Language Test Return",
      FR: "Test linguistique de retour",
      TIME: "R"
    }
  }
}
