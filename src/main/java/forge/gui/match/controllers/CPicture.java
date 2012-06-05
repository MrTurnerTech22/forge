/*
 * Forge: Play Magic: the Gathering.
 * Copyright (C) 2011  Forge Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package forge.gui.match.controllers;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import forge.Card;
import forge.CardCharactersticName;
import forge.Command;
import forge.gui.framework.ICDoc;
import forge.gui.match.views.VPicture;

/**
 * Controls the card picture panel in the match UI.
 * 
 * <br><br><i>(C at beginning of class name denotes a control class.)</i>
 */
public enum CPicture implements ICDoc {
    /** */
    SINGLETON_INSTANCE;

    private Card currentCard = null;
    private boolean flipped = false;

    /**
     * Shows card details and/or picture in sidebar cardview tabber.
     * 
     * @param c
     *            &emsp; Card object
     */
    public void showCard(final Card c) {
        this.currentCard = c;
        VPicture.SINGLETON_INSTANCE.getLblFlipcard().setVisible(c != null && c.isDoubleFaced() ? true : false);
        VPicture.SINGLETON_INSTANCE.getPnlPicture().setCard(c);
    }

    /**
     * Gets the current card.
     * 
     * @return Card
     */
    public Card getCurrentCard() {
        return this.currentCard;
    }

    /* (non-Javadoc)
     * @see forge.gui.framework.ICDoc#getCommandOnSelect()
     */
    @Override
    public Command getCommandOnSelect() {
        return null;
    }

    /* (non-Javadoc)
     * @see forge.gui.framework.ICDoc#initialize()
     */
    @Override
    public void initialize() {
        VPicture.SINGLETON_INSTANCE.getPnlPicture().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (VPicture.SINGLETON_INSTANCE.getPnlPicture().getCard() == null) { return; }

                if (VPicture.SINGLETON_INSTANCE.getPnlPicture().getCard().isDoubleFaced()) {
                    flipCard();
                }
            }
        });
    }

    /* (non-Javadoc)
     * @see forge.gui.framework.ICDoc#update()
     */
    @Override
    public void update() {
    }

    /** */
    public void flipCard() {
        if (flipped) {
            flipped = false;
            VPicture.SINGLETON_INSTANCE.getPnlPicture().getCard().setState(CardCharactersticName.Original);
            CDetail.SINGLETON_INSTANCE.showCard(this.currentCard);
        }
        else {
            flipped = true;
            VPicture.SINGLETON_INSTANCE.getPnlPicture().getCard().setState(CardCharactersticName.Transformed);
            CDetail.SINGLETON_INSTANCE.showCard(this.currentCard);
        }
        VPicture.SINGLETON_INSTANCE.getPnlPicture().setImage();
    }
}
