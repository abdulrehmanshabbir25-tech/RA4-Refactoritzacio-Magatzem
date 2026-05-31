/**
 * CODI REFACTORITZAT - COMMIT 8
 * Adaptació al patró Encapsulate Field.
 * Totes les consultes i modificacions d'atributs de l'article ara es fan mitjançant getters i setters.
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
            
            if (articles[i].getNom().equals("Martell de Thor (Llegendari)")) {
                continue; 
            }

            if (esArticleEstandard(articles[i])) {
                disminuirQualitatEstandard(articles[i]);
            } else {
                aumentarQualitatEspecials(articles[i]);
            }

            // Ús de Getter i Setter per als dies
            articles[i].setDiesPerVendre(articles[i].getDiesPerVendre() - 1);

            if (articles[i].getDiesPerVendre() < 0) {
                gestionarCaducitat(articles[i]);
            }
        }
    }

    private boolean esArticleEstandard(Article article) {
        return !article.getNom().equals("Formatge Gidurat")
                && !article.getNom().equals("Entrades per al Concert del Trobador");
    }

    private void disminuirQualitatEstandard(Article article) {
        if (article.getQualitat() > QUALITAT_MINIMA) {
            article.setQualitat(article.getQualitat() - 1);
        }
    }

    private void aumentarQualitatEspecials(Article article) {
        if (article.getQualitat() >= MAX_QUALITAT) {
            return; 
        }

        article.setQualitat(article.getQualitat() + 1);

        if (article.getNom().equals("Entrades per al Concert del Trobador")) {
            if (article.getDiesPerVendre() < LIMIT_DIES_URGENT && article.getQualitat() < MAX_QUALITAT) {
                article.setQualitat(article.getQualitat() + 1);
            }
            if (article.getDiesPerVendre() < LIMIT_DIES_CRITIC && article.getQualitat() < MAX_QUALITAT) {
                article.setQualitat(article.getQualitat() + 1);
            }
        }
    }

    private void gestionarCaducitat(Article article) {
        if (article.getNom().equals("Formatge Gidurat")) {
            if (article.getQualitat() < MAX_QUALITAT) {
                article.setQualitat(article.getQualitat() + 1);
            }
        } else if (article.getNom().equals("Entrades per al Concert del Trobador")) {
            article.setQualitat(0);
        } else {
            if (article.getQualitat() > QUALITAT_MINIMA) {
                article.setQualitat(article.getQualitat() - 1);
            }
        }
    }
}