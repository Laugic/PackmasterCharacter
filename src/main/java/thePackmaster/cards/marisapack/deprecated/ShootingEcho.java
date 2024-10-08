package thePackmaster.cards.marisapack.deprecated;

import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.upgradespack.ExhaustRandomPredicateCardAction;
import thePackmaster.cards.marisapack.AbstractMarisaCard;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.marisapack.BetterFireballEffect;
import thePackmaster.vfx.marisapack.MissileStrikeEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
public class ShootingEcho extends AbstractMarisaCard {
    public final static String ID = makeID(ShootingEcho.class.getSimpleName());
    private static final int DMG = 9, UPG_DMG = 2;

    public ShootingEcho() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = DMG;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new MissileStrikeEffect(m.hb.cX, m.hb.cY, BetterFireballEffect.randomFlareColor()), Settings.ACTION_DUR_FASTER);
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        Wiz.atb(new ExhaustRandomPredicateCardAction(c->true, Wiz.hand(), c->{
            ShootingEcho.this.resetAttributes();
            Wiz.makeInHand(ShootingEcho.this);
        }));
    }

    public void upp() {
        upgradeDamage(UPG_DMG);
    }
}
