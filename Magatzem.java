/**
 * CODI REFACTORITZAT - COMMIT 4
 * Patró aplicat: Extract Method (Extracció de Mètodes).
 * S'extreu la lògica de caducitat a un mètode privat per alleugerir el bucle principal.
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

            // Lògica de canvi de qualitat inicial
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

            // PATRÓ: Extract Method
            // Si l'article ha caducat, deleguem la feina al nou mètode especialitzat
            if (articles[i].diesPerVendre < 0) {
                gestionarCaducitat(articles[i]);
            }
        }
    }

    // NOU MÈTODE EXTRAÏT (Extract Method)
    // En lloc de sobrecarregar el mètode principal, aquest mètode només s'encarrega de la caducitat
    private void gestionarCaducitat(Article article) {
        if (!article.nom.equals("Formatge Gidurat")) {
            if (!article.nom.equals("Entrades per al Concert del Trobador")) {
                if (article.qualitat > QUALITAT_MINIMA) {
                    article.qualitat = article.qualitat - 1;
                }
            } else {
                article.qualitat = article.qualitat - article.qualitat;
            }
        } else {
            if (article.qualitat < MAX_QUALITAT) {
                article.qualitat = article.qualitat + 1;
            }
        }
    }
}