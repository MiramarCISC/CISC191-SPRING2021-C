package edu.sdccd.cisc191.c;

import java.util.Random;

public class Enemy extends Battler {

    private RegMon regularMon;
    private MagicMon magicMon;
    private boolean isMagic;

    public Enemy(String enName) {
        name = enName;
        stunned = false;
        burned = false;
        poisoned = false;
        asleep = false;
        presentSE = false;
        presentBuff = false;
        dead = false;

        exp = 0;
        durSE = 0;
        buffCD = 0;

    }

    /**
     * Nested class for the assignment: Intended for improved portability and readability while being able to modify
     * values of the parent class depending on the difficulty of the GridSquare that the battle can occur on, and
     * which kind of monster the enemy should be. This creates a built-in way to adapt difficulty into enemies.
     */
    public class EnemyBuilder {

        public EnemyBuilder(int difficulty, boolean magic) {

            /**
             * The enemy's level is determined based on difficulty.
             */
            int lvl = (int)Math.round(difficulty/5.0);
            if (lvl == 0) { lvl = 1; }

            /**
             * If the enemy is determined to be of a magical type, its casting ability is randomly determined,
             * and then its stats are generated from its monster type and level.
             * If the enemy is configured to be non-magical, it is made incapable of casting spells and its stats
             * are similarly determined based on its level (but with different modifiers).
             */
            if (magic) {
                regularMon = new RegMon("Not Used", 0, 0, 0, 0);

                isMagic = true;
                Random rand = new Random();
                int magType = rand.nextInt(3);

                boolean b = true;
                boolean w = true;
                switch (magType) {
                    case 0:
                        b = false;
                        w = true;
                        break;
                    case 1:
                        b = true;
                        w = false;
                        break;
                    case 2:
                        b = true;
                        w = true;
                        break;
                }

                magicMon = new MagicMon("Magic Grunt", 4, 4, 4, 15, 10, w, b);

                double hpWIP = magicMon.getBaseHP() * 1.0;
                double defWIP = magicMon.getBaseDef() * 1.0;
                double atkWIP = magicMon.getBaseAtk() * 1.0;
                double spdWIP = magicMon.getBaseSpd() * 1.0;
                double mpWIP = magicMon.getBaseMP() * 1.0;

                for (int i = 0; i < lvl; ++i) {
                    hpWIP = hpWIP * 1.2;
                    defWIP = defWIP * 1.2;
                    atkWIP = atkWIP * 1.2;
                    spdWIP = spdWIP * 1.2;
                    mpWIP = mpWIP * 1.2;
                }
                maxHP = (int) (Math.round(hpWIP));
                defStat = (int) (Math.round(defWIP));
                atkStat = (int) (Math.round(atkWIP));
                spdStat = (int) (Math.round(spdWIP));
                maxMP = (int) (Math.round(mpWIP));
            }
            else {
                magicMon = new MagicMon("Not Used", 0, 0, 0, 0, 0, false, false);

                isMagic = false;

                regularMon = new RegMon("Normal Grunt", 5, 5, 2, 17);

                double hpWIP = regularMon.getBaseHP() * 1.0;
                double defWIP = regularMon.getBaseDef() * 1.0;
                double atkWIP = regularMon.getBaseAtk() * 1.0;
                double spdWIP = regularMon.getBaseSpd() * 1.0;

                for (int i = 0; i < lvl; ++i) {
                    hpWIP = hpWIP * 1.3;
                    defWIP = defWIP * 1.3;
                    atkWIP = atkWIP * 1.3;
                    spdWIP = spdWIP * 1.1;
                }
                maxHP = (int) (Math.round(hpWIP));
                defStat = (int) (Math.round(defWIP));
                atkStat = (int) (Math.round(atkWIP));
                spdStat = (int) (Math.round(spdWIP));
                maxMP = 0;
            }

            /**
             * Since the enemy has just been created, its current stats are its max stats.
             */
            currHP = maxHP;
            currDef = defStat;
            currAtk = atkStat;
            currSpd = spdStat;
            currMP = maxMP;

        }

    }

    @Override
    protected void moveHappens() {

    }

}