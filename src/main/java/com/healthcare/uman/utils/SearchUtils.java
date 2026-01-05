package com.healthcare.uman.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class SearchUtils {
    private static final LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    public static final Set<String> nonRelevantWords = new HashSet<>(Arrays.asList(
            // Pronoms et articles
            "je", "tu", "il", "elle", "on", "nous", "vous", "ils", "elles", "me", "te", "se", "nous", "vous", "leur", "moi", "toi", "lui", "elle", "nous",
            "vous", "eux", "elles", "un", "une", "des", "du", "de", "la", "le", "les", "ce", "cet", "cette", "ces", "mon", "ton", "son", "ma", "ta", "sa",
            "mes", "tes", "ses", "notre", "votre", "leur", "nos", "vos",
            // Conjonctions
            "et", "ou", "mais", "donc", "or", "ni", "car", "si",
            // Verbes courants
            "être", "avoir", "faire", "pouvoir", "dire", "voir", "aller", "savoir", "falloir", "vouloir", "venir", "devoir", "prendre", "trouver", "donner",
            "parler", "mettre", "manger", "boire", "penser", "aimer", "croire", "sentir", "regarder", "entendre", "vivre", "partir", "rester", "passer",
            "travailler", "étudier", "retrouver", "rencontrer", "acheter", "vendre", "perdre", "gagner", "comprendre", "lire", "écrire", "chanter", "jouer",
            "courir", "nager", "voler", "marcher", "conduire",
            // Prépositions
            "sur", "pour", "avec", "sans", "sous", "entre", "contre", "par", "à", "dans",
            // Mots généraux
            "un", "une", "des", "du", "de", "la", "le", "les", "ce", "cet", "cette", "ces", "mon", "ton", "son", "ma", "ta", "sa", "mes", "tes", "ses", "notre",
            "votre", "leur", "nos", "vos"));

    public static final Map<String, Set<String>> synonymDictionary = new HashMap<>();

    static {
        synonymDictionary.put("blessure", new HashSet<>(Arrays.asList("blessée", "blessé", "blesser")));
        synonymDictionary.put("fatigue", new HashSet<>(Arrays.asList("épuisement", "lassitude", "affaiblissement")));
        synonymDictionary.put("fièvre", new HashSet<>(Arrays.asList("température élevée", "hyperthermie")));
        synonymDictionary.put("toux", new HashSet<>(Arrays.asList("toussotement", "quinte de toux")));
        synonymDictionary.put("douleur", new HashSet<>(Arrays.asList("souffrance", "mal", "aigreur")));
        synonymDictionary.put("malaise", new HashSet<>(Arrays.asList("inconfort", "gêne", "indisposition")));
        synonymDictionary.put("grippe", new HashSet<>(Arrays.asList("influenza", "virus grippal")));
        synonymDictionary.put("allergies", new HashSet<>(Arrays.asList("réactions allergiques", "hypersensibilité")));
        synonymDictionary.put("entorse", new HashSet<>(Arrays.asList("torse", "blessure", "traumatisme")));
        synonymDictionary.put("foulure", new HashSet<>(Arrays.asList("entorse", "blessure", "traumatisme")));
        synonymDictionary.put("muscle", new HashSet<>(Arrays.asList("tissu musculaire", "fibres musculaires", "tendons")));
        synonymDictionary.put("fracture", new HashSet<>(Arrays.asList("casse", "rupture", "fêlure")));
        synonymDictionary.put("élongation", new HashSet<>(Arrays.asList("étirement", "allongement", "tension")));
        synonymDictionary.put("sport", new HashSet<>(
                Arrays.asList("football", "foot", "basketball", "basket", "tennis", "natation", "athlétisme", "volleyball", "rugby", "golf", "cyclisme", "boxe",
                        "badminton", "bad", "escrime", "haltérophilie", "karaté", "judo", "course à pied", "course", "escalade", "ski", "snowboard", "surf",
                        "plongée", "voile", "athlétisme", "gymnastique", "gym", "triathlon", "aviron", "handball", "baseball", "softball", "pétanque", "paddle",
                        "course de chevaux", "course de voitures", "sports nautiques", "sports d'hiver", "running", "training")));
        synonymDictionary.put("blessure", new HashSet<>(Arrays.asList("traumatisme", "lésion", "contusion")));
        synonymDictionary.put("tendinite", new HashSet<>(Arrays.asList("inflammation des tendons", "tendinopathie", "tendinose")));
        synonymDictionary.put("crampes", new HashSet<>(Arrays.asList("contractions musculaires", "spasmes", "crises")));
        synonymDictionary.put("échauffement", new HashSet<>(Arrays.asList("préparation", "mise en condition", "éveil musculaire")));
        synonymDictionary.put("performance", new HashSet<>(Arrays.asList("rendement", "exploit", "réalisation")));
        synonymDictionary.put("récupération", new HashSet<>(Arrays.asList("repos", "régénération", "convalescence")));
        synonymDictionary.put("équipement", new HashSet<>(Arrays.asList("matériel", "attirail", "instrumentation")));
        synonymDictionary.put("hydratation", new HashSet<>(Arrays.asList("apport en eau", "hydratation cellulaire", "assèchement")));
        synonymDictionary.put("nutrition", new HashSet<>(Arrays.asList("alimentation", "nourriture", "régime alimentaire")));

    }

    /**
     * Checks if a keyword is similar to any word in the given text, using the Levenshtein distance algorithm.
     * A keyword is considered similar if its distance to any word in the text is less than or equal to a threshold.
     *
     * @param keyword The keyword to check for similarity.
     * @param text The text against which to compare the keyword.
     *
     * @return true if the keyword is similar to any word in the text, false otherwise.
     */
    public static boolean isSimilar(String keyword, String text) {
        int threshold = 1;
        String[] words = text.split("\\s+");
        for (String word : words) {
            // Ignorer les pronoms
            if (nonRelevantWords.contains(word.toLowerCase())) {
                continue;
            }
            int distance = levenshteinDistance.apply(keyword.toLowerCase(), word.toLowerCase());
            if (distance <= threshold) {
                return true;
            }
        }
        return false;
    }
}
