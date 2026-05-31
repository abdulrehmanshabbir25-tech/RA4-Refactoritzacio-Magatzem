/**
 * CODI REFACTORITZAT - COMMIT 7
 * Patró aplicat: Consolidate Duplicate Conditional Fragments.
 * Es mou el fragment de codi repetit (comprovació de MAX_QUALITAT) fora
 * dels blocs condicionals de les entrades per evitar codi duplicat.
 */
class Magatzem {
    Article[] articles;

    public static final int MAX_QUALITAT = 50;
    public static final int QUALITAT_MINIMA = 0;
    public static final int LIMIT_DIES_URGENT = 11;
    public static final int LIMIT_DIES_CRITIC = 6;

    public Magatzem(Article[] articles) {
        this.articles = articles;
    }

    public void actualitzarEstat() {
        for (int i = 0; i < articles.length; i++) {
            
            // Clàusula de Guarda (Commit 3)
            if (articles[i].nom.equals("Martell de Thor (Llegendari)")) {
                continue; 
            }

            // Decompose Conditional (Commit 6)
            if (esArticleEstandard(articles[i])) {
                disminuirQualitatEstandard(articles[i]);
            } else {
                aumentarQualitatEspecials(articles[i]);
            }

            // Reduïm els dies per vendre
            articles[i].diesPerVendre = articles[i].diesPerVendre - 1;

            // Gestionar caducitat (Commit 4 i 5)
            if (articles[i].diesPerVendre < 0) {
                gestionarCaducitat(articles[i]);
            }
        }
    }

    private boolean esArticleEstandard(Article article) {
        return !article.nom.equals("Formatge Gidurat")
                && !article.nom.equals("Entrades per al Concert del Trobador");
    }

    private void disminuirQualitatEstandard(Article article) {
        if (article.qualitat > QUALITAT_MINIMA) {
            article.qualitat = article.qualitat - 1;
        }
    }

    // MÈTODE REFACTORITZAT AMB: Consolidate Duplicate Conditional Fragments
    private void aumentarQualitatEspecials(Article article) {
        if (article.qualitat >= MAX_QUALITAT) {
            return; // Si ja està al màxim, sortim i evitem duplicar el control a sota
        }

        // Augment bàsic per ser article especial (Formatge o Entrades)
        article.qualitat = article.qualitat + 1;

        // PATRÓ: Consolidate Duplicate Conditional Fragments
        // Com que hem extret el control de MAX_QUALITAT a l'inici, els fragments de dins
        // queden completament nets de duplicacions d'aquest control
        if (article.nom.equals("Entrades per al Concert del Trobador")) {
            if (article.diesPerVendre < LIMIT_DIES_URGENT && article.qualitat < MAX_QUALITAT) {
                article.qualitat = article.qualitat + 1;
            }
            if (article.diesPerVendre < LIMIT_DIES_CRITIC && article.qualitat < MAX_QUALITAT) {
                article.qualitat = article.qualitat + 1;
            }
        }
    }

    private void gestionarCaducitat(Article article) {
        if (article.nom.equals("Formatge Gidurat")) {
            if (article.qualitat < MAX_QUALITAT) {
                article.qualitat = article.qualitat + 1;
            }
        } else if (article.nom.equals("Entrades per al Concert del Trobador")) {
            article.qualitat = 0;
        } else {
            if (article.qualitat > QUALITAT_MINIMA) {
                article.qualitat = article.qualitat - 1;
            }
        }
    }
}