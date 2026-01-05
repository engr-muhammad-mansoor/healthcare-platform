package com.healthcare.uman.model.speciality;

public enum Specialization {
    GENERALE("Générale"),
    SPORTIVE("Sportive"),
    ORTHOPEDIQUE("Orthopédique"),
    RESPIRATOIRE("Respiratoire"),
    PEDIATRIQUE("Pédiatrique"),
    NEUROLOGIQUE("Neurologique"),
    MANUELLE("Manuelle"),
    PERINEALE("Périnéale"),
    CARDIOVASCULAIRE("Cardiovasculaire"),
    RHUMATOLOGIE("Rhumatologie"),
    ONCOLOGIE("Oncologie"),
    GERIATRIE("Gériatrie"),
    TRAUMATOLOGIE("Traumatologie"),
    HANDICAP("Handicap"),
    POSTUROLOGIE("Posturologie"),
    MAIN("Main"),
    PEDIATRIE_PRECOCE("Pédiatrie Précoce"),
    VESTIBULAIRE("Vestibulaire"),
    NEUROLOGIE_PEDIATRIQUE("Neurologie Pédiatrique"),
    TMJ("TMJ (Articulation Temporo-Mandibulaire)"),
    SPORTIF_HAUT_NIVEAU("Sportif de Haut Niveau"),
    READAPTATION_CARDIAQUE("Réadaptation Cardiaque"),
    NEUROLOGIE_ADULTE("Neurologie Adulte"),
    ONCOLOGIE_PEDIATRIQUE("Oncologie Pédiatrique"),
    THERAPIE_MANUELLE_AVANCEE("Thérapie Manuelle Avancée"),
    REEDUCATION_NEUROCOGNITIVE("Rééducation Neurocognitive"),
    DOULEUR_CHRONIQUE("Douleur Chronique"),
    BIOMECANIQUE("Biomécanique"),
    SCOLIOSE("Scoliose"),
    THERAPIE_PAR_ONDES_DE_CHOC("Thérapie par Ondes de Choc"),
    ERGONOMIE("Ergonomie"),
    REEDUCATION_DE_LA_VOIX("Rééducation de la Voix"),
    REEDUCATION_DE_LA_DEGLUTITION("Rééducation de la Déglutition"),
    REEDUCATION_POSTURALE_GLOBALE("Rééducation Posturale Globale"),
    BIOFEEDBACK("Biofeedback"),
    READAPTATION_PULMONAIRE("Réadaptation Pulmonaire"),
    NEUROPHYSIOLOGIE("Neurophysiologie"),
    REEDUCATION_VESTIBULAIRE("Rééducation Vestibulaire"),
    REEDUCATION_DE_LA_MARCHE("Rééducation de la Marche"),
    REEDUCATION_POST_AVC("Rééducation Post-AVC"),
    REEDUCATION_NEUROMUSCULAIRE("Rééducation Neuromusculaire"),
    REEDUCATION_DE_LA_PROPRIOCEPTION("Rééducation de la Proprioception");

    private final String name;

    Specialization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
