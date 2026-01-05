package com.healthcare.uman.config.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.healthcare.uman.model.speciality.Specialization;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ChangeLog(order = "002")
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "createSpecialities", author = "perrine")
    public void createSpecialities(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("speciality");

        for (Specialization specialization : Specialization.values()) {
            if (collection.countDocuments(Filters.eq("name", specialization.name())) == 0) {
                collection.insertOne(new Document("name", specialization.name()));
            }
        }
    }

    @ChangeSet(order = "002", id = "addSpecialitiesWithKeywords", author = "perrine")
    public void addSpecialitiesWithKeywords(MongoDatabase db) {
        MongoCollection<Document> collection = db.getCollection("speciality");

        Map<String, List<String>> specialitiesWithKeywords = new HashMap<>();
        specialitiesWithKeywords.put(Specialization.GENERALE.name(), Arrays.asList("fatigue", "fièvre", "toux", "douleur", "malaise", "grippe", "allergies", "vaccin", "check-up", "santé", "tête", "poids", "déshydratation", "nausée"));
        specialitiesWithKeywords.put(Specialization.SPORTIVE.name(), Arrays.asList("entorse", "foulure", "muscle", "fracture", "élongation", "sport", "blessure", "tendinite", "crampes", "échauffement", "performance", "récupération", "équipement", "hydratation", "nutrition"));
        specialitiesWithKeywords.put(Specialization.ORTHOPEDIQUE.name(), Arrays.asList("dos", "genou", "épaule", "arthrose", "prothèse", "sciatique", "lombalgie", "ostéoporose", "hernie", "discale", "raideur", "mouvement", "chirurgie", "rééducation", "cervicale"));
        specialitiesWithKeywords.put(Specialization.RESPIRATOIRE.name(), Arrays.asList("respirer", "essoufflement", "toux", "asthme", "bronchite", "pneumonie", "apnée", "sommeil", "BPCO", "tuberculose", "respiration", "sifflement", "expectorations", "infections", "oxygénothérapie", "souffle", "inhalateur"));
        specialitiesWithKeywords.put(Specialization.PEDIATRIQUE.name(), Arrays.asList("fièvre enfant", "vaccination enfant", "croissance", "développement", "éruption cutanée", "otite", "varicelle", "rougeole", "coliques", "allaitement", "nutrition infantile", "pleurs", "sommeil bébé", "poids bébé", "éducation parentale"));
        specialitiesWithKeywords.put(Specialization.NEUROLOGIQUE.name(), Arrays.asList("maux de tête", "vertiges", "engourdissement", "tremblements", "épilepsie", "AVC", "migraine", "perte de mémoire", "sclérose en plaques", "maladie de Parkinson", "paralysie", "troubles du sommeil", "névralgie", "démence", "coordination"));
        specialitiesWithKeywords.put(Specialization.MANUELLE.name(), Arrays.asList("douleur de dos", "blocage", "massage", "mobilisation", "manipulation", "détente musculaire", "tension", "relaxation", "posture", "douleur cervicale", "douleur lombaire", "soulagement douleur", "rééquilibrage", "flexibilité", "respiration"));
        specialitiesWithKeywords.put(Specialization.PERINEALE.name(), Arrays.asList("incontinence", "rééducation périnée", "grossesse", "post-partum", "fuites urinaires", "douleur pelvienne", "prolapsus", "rééducation après accouchement", "contrôle vessie", "sexualité", "renforcement périnéal", "exercices Kegel", "santé féminine", "accouchement", "rééducation sexuelle"));
        specialitiesWithKeywords.put(Specialization.CARDIOVASCULAIRE.name(), Arrays.asList("pression artérielle", "douleur poitrine", "palpitations", "infarctus", "insuffisance cardiaque", "cholestérol", "athérosclérose", "arythmie", "électrocardiogramme", "épreuve d'effort", "angioplastie", "maladie coronarienne", "réadaptation cardiaque", "hypertension", "souffle au cœur"));
        specialitiesWithKeywords.put(Specialization.RHUMATOLOGIE.name(), Arrays.asList("douleurs articulaires", "gonflement articulations", "arthrite", "lupus", "goutte", "douleurs musculaires", "fibromyalgie", "ostéoporose", "spondylarthrite", "douleur au cou", "douleur au dos", "polyarthrite", "injections articulaires", "rééducation rhumatologique", "anti-inflammatoires"));
        specialitiesWithKeywords.put(Specialization.ONCOLOGIE.name(), Arrays.asList("cancer", "tumeur", "chimiothérapie", "radiation", "oncologie", "nausées", "perte de cheveux", "biopsie", "mammographie", "cancer du sein", "cancer du poumon", "tumeur cérébrale", "soins de support", "surveillance", "rémission"));
        specialitiesWithKeywords.put(Specialization.GERIATRIE.name(), Arrays.asList("vieillissement", "perte d'autonomie", "mémoire", "démence", "Alzheimer", "soins gériatriques", "chute", "polymédication", "incontinence", "isolement", "soins à domicile", "aides techniques", "prothèse", "nutrition âgée", "activité physique"));
        specialitiesWithKeywords.put(Specialization.TRAUMATOLOGIE.name(), Arrays.asList("accident", "fracture", "plaie", "contusion", "entorse", "lésion", "rééducation", "immobilisation", "chirurgie réparatrice", "douleur traumatique", "réhabilitation", "suivi post-opératoire", "traumatisme crânien", "urgence", "soins intensifs"));
        specialitiesWithKeywords.put(Specialization.HANDICAP.name(), Arrays.asList("accessibilité", "autonomie", "aides techniques", "fauteuil roulant", "aménagement domicile", "intégration", "emploi handicap", "éducation inclusive", "handicap moteur", "handicap sensoriel", "prothèse", "réadaptation", "mobilité", "transport adapté", "vie sociale"));
        specialitiesWithKeywords.put(Specialization.POSTUROLOGIE.name(), Arrays.asList("mauvaise posture", "mal de dos", "équilibre", "stabilité", "rééducation posturale", "douleur cervicale", "douleur lombaire", "exercices posturaux", "correction posture", "biomécanique", "troubles musculo-squelettiques", "analyse de la marche", "posture assise", "ergonomie", "alignement corporel"));
        specialitiesWithKeywords.put(Specialization.SCOLIOSE.name(), Arrays.asList("dos courbé", "déviation colonne", "douleur dos", "corset", "exercices correction", "suivi enfant", "croissance", "kinésithérapie", "chirurgie scoliose", "surveillance", "douleur scoliose", "posture", "renforcement musculaire", "flexibilité", "alignement vertébral"));
        specialitiesWithKeywords.put(Specialization.THERAPIE_PAR_ONDES_DE_CHOC.name(), Arrays.asList("douleur persistante", "tendinite", "épicondylite", "calcification", "traitement douleur", "récupération tissulaire", "soulagement rapide", "thérapie non invasive", "ondes acoustiques", "réduction inflammation", "stimulation circulation", "régénération cellulaire", "tissus mous", "réhabilitation", "efficacité clinique"));
        specialitiesWithKeywords.put(Specialization.ERGONOMIE.name(), Arrays.asList("douleur bureau", "travail ordinateur", "aménagement poste", "confort travail", "prévention TMS", "posture travail", "santé au travail", "ergonomie bureau", "évaluation risques", "conseil ergonomique", "mobilier ergonomique", "pauses actives", "organisation travail", "gestes et postures", "équipement adapté"));
        specialitiesWithKeywords.put(Specialization.REEDUCATION_DE_LA_VOIX.name(), Arrays.asList("problèmes vocaux", "perte voix", "voix enrouée", "rééducation vocale", "orthophonie", "technique vocale", "soins des cordes vocales", "troubles de la parole", "respiration", "articulation", "voix chantée", "voix parlée", "hygiène vocale", "exercices vocaux", "résonance"));
        specialitiesWithKeywords.put(Specialization.REEDUCATION_DE_LA_DEGLUTITION.name(), Arrays.asList("difficulté à avaler", "dysphagie", "alimentation adaptée", "rééducation déglutition", "fausses routes", "sécurité alimentaire", "texture modifiée", "hydratation", "techniques déglutition", "stimulation orale", "réflexe déglutitoire", "nutrition", "suivi nutritionnel", "positionnement", "contrôle salivaire"));
        specialitiesWithKeywords.put(Specialization.REEDUCATION_POSTURALE_GLOBALE.name(), Arrays.asList("réalignement corporel", "méthode RPG", "douleur posturale", "rééducation globale", "tension musculaire", "amélioration posture", "renforcement global", "flexibilité", "posture dynamique", "soulagement douleur", "correction musculaire", "équilibre", "mobilité", "posture statique", "technique respiratoire"));
        specialitiesWithKeywords.put(Specialization.BIOFEEDBACK.name(), Arrays.asList("contrôle physiologique", "relaxation", "gestion stress", "rééducation fonctionnelle", "mesures physiologiques", "feedback visuel", "feedback auditif", "techniques relaxation", "conscience corporelle", "amélioration performance", "réduction douleur", "biofeedback musculaire", "biofeedback respiratoire", "auto-régulation", "apprentissage autonome"));
        specialitiesWithKeywords.put(Specialization.READAPTATION_PULMONAIRE.name(), Arrays.asList("rééducation respiratoire", "BPCO", "exercices respiratoires", "amélioration fonction pulmonaire", "endurance", "capacité respiratoire", "séances d'oxygénothérapie"));
        specialitiesWithKeywords.put(Specialization.NEUROPHYSIOLOGIE.name(), Arrays.asList("études neurophysiologiques", "EMG", "potentiels évoqués", "diagnostic neurologique", "fonction nerveuse"));
        specialitiesWithKeywords.put(Specialization.REEDUCATION_VESTIBULAIRE.name(), Arrays.asList("vertiges", "troubles de l'équilibre", "rééducation vestibulaire", "maladie de Ménière", "réadaptation vestibulaire"));
        specialitiesWithKeywords.put(Specialization.REEDUCATION_DE_LA_MARCHE.name(), Arrays.asList("troubles de la marche", "rééducation de la marche", "analyse de la marche", "amélioration de la mobilité", "marche avec assistance"));
        specialitiesWithKeywords.put(Specialization.REEDUCATION_POST_AVC.name(), Arrays.asList("rééducation post-AVC", "récupération motrice", "aphasie", "hémiplégie", "réadaptation neurologique"));
        specialitiesWithKeywords.put(Specialization.REEDUCATION_NEUROMUSCULAIRE.name(), Arrays.asList("maladies neuromusculaires", "force musculaire", "endurance musculaire", "coordination", "rééducation fonctionnelle"));
        specialitiesWithKeywords.put(Specialization.REEDUCATION_DE_LA_PROPRIOCEPTION.name(), Arrays.asList("sens proprioceptif", "équilibre", "coordination", "stabilité articulaire", "prévention des entorses"));

        specialitiesWithKeywords.forEach((name, keywords) -> {
            Document speciality = collection.find(Filters.eq("name", name)).first();
            if (speciality != null) {
                collection.updateOne(Filters.eq("name", name), new Document("$set", new Document("keywords", keywords)));
            } else {
                collection.insertOne(new Document("name", name).append("keywords", keywords));
            }
        });
    }
}
