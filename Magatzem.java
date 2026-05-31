/**
 * CODI REFACTORITZAT FINAL - COMMIT 9
 * Classe Magatzem totalment alliberada de responsabilitats de baix nivell.
 * Només s'encarrega d'iterar el magatzem i delegar el comportament a cada Article.
 */
class Magatzem {
    Article[] articles;

    public Magatzem(Article[] articles) {
        this.articles = articles;
    }

    public void actualitzarEstat() {
        for (int i = 0; i < articles.length; i++) {
            
            // Clàusula de Guarda (Commit 3)
            if (articles[i].getNom().equals("Martell de Thor (Llegendari)")) {
                continue; 
            }

            // PATRÓ: Move Method (Cridem als mètodes que ara pertanyen a Article)
            if (articles[i].esArticleEstandard()) {
                articles[i].disminuirQualitatEstandard();
            } else {
                articles[i].aumentarQualitatEspecials();
            }

            // Reduïm els dies per vendre de l'article
            articles[i].setDiesPerVendre(articles[i].getDiesPerVendre() - 1);

            // Delegació final de la caducitat a l'objecte encarregat
            if (articles[i].getDiesPerVendre() < 0) {
                articles[i].gestionarCaducitat();
            }
        }
    }
}