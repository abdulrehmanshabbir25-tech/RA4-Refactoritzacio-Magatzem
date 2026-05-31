/**
 * CODI REFACTORITZAT - COMMIT 6
 * Patró aplicat: Decompose Conditional.
 * S'extreu la condició complexa de l'if, el bloc 'then' i el bloc 'else' 
 * en mètodes separats segons especifica el PDF de l'assignatura.
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

            // PATRÓ: Decompose Conditional
            // Substituïm l'if complex per mètodes separats (condició, then, else)
            if (esArticleEstandard(articles[i])) {
                disminuirQualitatEstandard(articles[i]); // Bloc Then
            } else {
                aumentarQualitatEspecials(articles[i]); // Bloc Else
            }

            // Reduïm els dies per vendre
            articles[i].diesPerVendre = articles[i].diesPerVendre - 1;

            // Gestionar caducitat (Commit 4 i 5)
            if (articles[i].diesPerVendre < 0) {
                gestionarCaducitat(articles[i]);
            }
        }
    }

    // 1. PARTS EXTRETES DEL PATRÓ: Decompose Conditional

    // Mètode per a la Condició (if)
    private boolean esArticleEstandard(Article article) {
        return !article.nom.equals("Formatge Gidurat")
                && !article.nom.equals("Entrades per al Concert del Trobador");
    }

    // Mètode per al bloc THEN
    private void disminuirQualitatEstandard(Article article) {
        if (article.qualitat > QUALITAT_MINIMA) {
            article.qualitat = article.qualitat - 1;
        }
    }

    // Mètode per al bloc ELSE
    private void aumentarQualitatEspecials(Article article) {
        if (article.qualitat < MAX_QUALITAT) {
            article.qualitat = article.qualitat + 1;

            if (article.nom.equals("Entrades per al Concert del Trobador")) {
                if (article.diesPerVendre < LIMIT_DIES_URGENT && article.qualitat < MAX_QUALITAT) {
                    article.qualitat = article.qualitat + 1;
                }
                if (article.diesPerVendre < LIMIT_DIES_CRITIC && article.qualitat < MAX_QUALITAT) {
                    article.qualitat = article.qualitat + 1;
                }
            }
        }
    }

    // Mètode de caducitat (Commit 5)
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