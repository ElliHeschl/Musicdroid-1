/**
 *  Catroid: An on-device visual programming system for Android devices
 *  Copyright (C) 2010-2013 The Catrobat Team
 *  (<http://developer.catrobat.org/credits>)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  An additional term exception under section 7 of the GNU Affero
 *  General Public License, version 3, is available at
 *  http://developer.catrobat.org/license_additional_term
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.catrobat.musicdroid.pocketmusic.note.draw;

import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Point;

import org.catrobat.musicdroid.pocketmusic.note.MusicalKey;
import org.catrobat.musicdroid.pocketmusic.note.symbol.Symbol;

public abstract class SymbolDrawer {

    public static final float SYMBOL_WIDTH_MULTIPLIER = 3f;
    public static final float SMALL_SYMBOL_WIDTH_MULTIPLIER = 1f / 4f;

    protected NoteSheetCanvas canvas;
    protected Paint paint;
    protected Resources resources;
    protected MusicalKey key;
    protected NoteSheetDrawPosition drawPosition;
    protected int distanceBetweenLines;

    protected final int widthForOneSymbol;
    protected final int widthForOneSmallSymbol;

	public SymbolDrawer(NoteSheetCanvas canvas, Paint paint, Resources resources, MusicalKey key, NoteSheetDrawPosition drawPosition, int distanceBetweenLines) {
		this.canvas = canvas;
        this.paint = paint;
        this.resources = resources;
        this.key = key;
        this.drawPosition = drawPosition;
        this.distanceBetweenLines = distanceBetweenLines;

        widthForOneSymbol = (int) (distanceBetweenLines * SYMBOL_WIDTH_MULTIPLIER);
        widthForOneSmallSymbol = (int) (widthForOneSymbol * SMALL_SYMBOL_WIDTH_MULTIPLIER);
	}

    private Point getCenterPointForNextSymbol(int symbolWidth) {
        Point centerPoint = new Point(drawPosition.getStartXPositionForNextElement() + (symbolWidth / 2), canvas.getHeightHalf());

        drawPosition.increasesStartXPositionForNextElement(symbolWidth);

        return centerPoint;
    }

    protected Point getCenterPointForNextSymbol() {
        return getCenterPointForNextSymbol(widthForOneSymbol);
    }

    protected Point getCenterPointForNextSmallSymbol() {
        return getCenterPointForNextSymbol(widthForOneSmallSymbol);
    }

    public abstract void drawSymbol(Symbol symbol);
}
