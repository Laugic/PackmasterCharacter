package thePackmaster.cards.dimensiongateabstracts;

public abstract class AbstractDimensionalCardInscryp extends AbstractDimensionalCard {

    public AbstractDimensionalCardInscryp(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target) {
        super(cardID, cost, rarity, type, target, "dimension/inscryp");
    }

    public AbstractDimensionalCardInscryp(final String cardID, final int cost, final CardRarity rarity, final CardType type, final CardTarget target, final CardColor color) {
        super(cardID, cost, rarity, type, target, "dimension/inscryp", color);
    }

}