package theOlympian.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theOlympian.DefaultMod;
import theOlympian.characters.TheDefault;
import theOlympian.powers.JoltedEnemyPower;

import static theOlympian.DefaultMod.makeCardPath;

public class Electrocute extends AbstractDynamicCard {

    // TEXT DECLARATION

    // This does mean that you will need to have an image with the same NAME as the card in your image folder for it to run correctly.
    public static final String ID = DefaultMod.makeID(Electrocute.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = TheDefault.Enums.COLOR_GRAY;

    private static final int COST = 1;


    // /STAT DECLARATION/


    public Electrocute() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (m != null && m.hasPower(JoltedEnemyPower.POWER_ID)) {
            System.out.println("Has Jolted");
            int target_jolted = m.getPower(JoltedEnemyPower.POWER_ID).amount;

            if (!this.upgraded) {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, target_jolted, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            } else {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, target_jolted+2, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
        }
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
