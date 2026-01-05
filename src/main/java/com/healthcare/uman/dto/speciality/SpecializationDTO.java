package com.healthcare.uman.dto.speciality;

public enum SpecializationDTO {
    GENERALE("GEnErale"),
    SPORTIVE("Sportive"),
    ORTHOPEDIQUE("OrthopEdique"),
    RESPIRATOIRE("Respiratoire"),
    PEDIATRIQUE("PEdiatrique"),
    NEUROLOGIQUE("Neurologique"),
    MANUELLE("Manuelle"),
    PERINEALE("PErinEale"),
    CARDIOVASCULAIRE("Cardiovasculaire"),
    RHUMATOLOGIE("Rhumatologie"),
    ONCOLOGIE("Oncologie"),
    GERIATRIE("GEriatrie"),
    TRAUMATOLOGIE("Traumatologie"),
    HANDICAP("Handicap"),
    POSTUROLOGIE("Posturologie"),
    MAIN("Main"),
    PEDIATRIE_PRECOCE("PEdiatrie PrEcoce"),
    VESTIBULAIRE("Vestibulaire"),
    NEUROLOGIE_PEDIATRIQUE("Neurologie PEdiatrique"),
    TMJ("TMJ (articulation temporo-mandibulaire)"),
    SPORTIF_HAUT_NIVEAU("Sportif de Haut Niveau"),
    READAPTATION_CARDIAQUE("REadaptation Cardiaque"),
    NEUROLOGIE_ADULTE("Neurologie Adulte"),
    ONCOLOGIE_PEDIATRIQUE("Oncologie PEdiatrique"),
    THERAPIE_MANUELLE_AVANCEE("ThErapie Manuelle AvancEe"),
    REEDUCATION_NEUROCOGNITIVE("REEducation Neurocognitive"),
    DOULEUR_CHRONIQUE("Douleur Chronique"),
    BIOMECANIQUE("BiomEcanique"),
    SCOLIOSE("Scoliose"),
    THERAPIE_PAR_ONDES_DE_CHOC("ThErapie par Ondes de Choc"),
    ERGONOMIE("Ergonomie"),
    REEDUCATION_DE_LA_VOIX("REEducation de la Voix"),
    REEDUCATION_DE_LA_DEGLUTITION("REEducation de la DEglutition"),
    REEDUCATION_POSTURALE_GLOBALE("REEducation Posturale Globale"),
    BIOFEEDBACK("Biofeedback"),
    READAPTATION_PULMONAIRE("REadaptation Pulmonaire"),
    NEUROPHYSIOLOGIE("Neurophysiologie"),
    REEDUCATION_VESTIBULAIRE("REEducation Vestibulaire"),
    REEDUCATION_DE_LA_MARCHE("REEducation de la Marche"),
    REEDUCATION_POST_AVC("REEducation Post-AVC"),
    REEDUCATION_NEUROMUSCULAIRE("REEducation Neuromusculaire"),
    REEDUCATION_DE_LA_PROPRIOCEPTION("REEducation de la Proprioception");

    private final String name;

    SpecializationDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
