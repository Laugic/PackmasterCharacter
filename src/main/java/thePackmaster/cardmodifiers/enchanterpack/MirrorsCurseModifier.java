package thePackmaster.cardmodifiers.enchanterpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import thePackmaster.cards.enchanterpack.MirrorsCurse;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

@AbstractCardModifier.SaveIgnore
public class MirrorsCurseModifier extends AbstractCardModifier {

    MirrorsCurse toPlayCard = null;

    public MirrorsCurseModifier(MirrorsCurse original) {
        toPlayCard = (MirrorsCurse) original.makeStatEquivalentCopy();
    }

    public void onInitialApplication(AbstractCard card) {
        if (card.cardsToPreview != null) {
            MultiCardPreview.add(card, card.cardsToPreview);
            card.cardsToPreview = null;
        }
        MultiCardPreview.add(card, toPlayCard);
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        MirrorsCurse cpy = (MirrorsCurse) toPlayCard.makeSameInstanceOf();
        cpy.neighbors = getCardNeighbors(card);
        cpy.hasRightNeighbors = true;
        cpy.purgeOnUse = true;
        Wiz.atb(new NewQueueCardAction(cpy, true, false, true));
    }

    private ArrayList<AbstractCard> getCardNeighbors(AbstractCard c) {
        ArrayList<AbstractCard> neighbors = new ArrayList<>();
        if (Wiz.hand().contains(c)) {
            int index = Wiz.hand().group.indexOf(c);
            if (index > 0) {
                neighbors.add(Wiz.hand().group.get(index -1));
            }
            if (index < Wiz.hand().size() - 1) {
                neighbors.add(Wiz.hand().group.get(index + 1));
            }
        }
        return neighbors;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new MirrorsCurseModifier(toPlayCard);
    }
}
