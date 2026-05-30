/**
 * CODI REFACTORITZAT - COMMIT 5
 * Patró aplicat: Consolidate Conditional Expression.
 * S'agrupen i simplifiquen les condicions de caducitat en una sola línia lògica.
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

            // Lògica de qualitat inicial
            if (!articles[i].nom.equals("Formatge Gidurat")
                    && !articles[i].nom.equals("Entrades per al Concert del Trobador")) {
                if (articles[i].qualitat > QUALITAT_MINIMA) {
                    articles[i].qualitat = articles[i].qualitat - 1;
                }
            } else {
                if (articles[i].qualitat < MAX_QUALITAT) {
                    articles[i].qualitat = articles[i].qualitat + 1;

                    if (articles[i].nom.equals("Entrades per al Concert del Trobador")) {
                        if (articles[i].diesPerVendre < LIMIT_DIES_URGENT) {
                            if (articles[i].qualitat < MAX_QUALITAT) {
                                articles[i].qualitat = articles[i].qualitat + 1;
                            }
                        }

                        if (articles[i].diesPerVendre < LIMIT_DIES_CRITIC) {
                            if (articles[i].qualitat < MAX_QUALITAT) {
                                articles[i].qualitat = articles[i].qualitat + 1;
                            }
                        }
                    }
                }
            }

            // Reduïm els dies per vendre
            articles[i].diesPerVendre = articles[i].diesPerVendre - 1;

            // Mètode extraït anteriorment (Commit 4)
            if (articles[i].diesPerVendre < 0) {
                gestionarCaducitat(articles[i]);
            }
        }
    }

    // MÈTODE CORREGIT AMB: Consolidate Conditional Expression
    private void gestionarCaducitat(Article article) {
        if (article.nom.equals("Formatge Gidurat")) {
            if (article.qualitat < MAX_QUALITAT) {
                article.qualitat = article.qualitat + 1;
            }
        } else if (article.nom.equals("Entrades per al Concert del Trobador")) {
            // Simplificació directa: passar la qualitat a 0 (qualitat - qualitat = 0)
            article.qualitat = 0;
        } else {
            // PATRÓ: Consolidate Conditional Expression
            // Agrupem aquí qualsevol altre article normal que mantingui qualitat per sobre del mínim
            if (article.qualitat > QUALITAT_MINIMA) {
                article.qualitat = article.qualitat - 1;
            }
        }
    }
}