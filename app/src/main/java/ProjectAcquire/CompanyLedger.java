/**
 * CompanyLedger.java
 *
 * MIT License
 *
 * Copyright (c) 2021 404
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * @author Team 404
 * @version v1.1.0
 */


//ALEX NOTE: This is a really bad way to do things, this is kind of like a local database that only holds a couple fields
//I did it this way so that I could store a chartering tile outside of the class where it is instantiated.
//If I had more time I would rework this greately, as it is codes equivalent of a dangling participle

package ProjectAcquire;
import lombok.Getter;
import lombok.Setter;

/**
 * Holds a instance variable of the tile and company that we just placed on the board.
 * This is to avoid passing a played tile through many classes when needed.
 */
@Setter @Getter
class CompanyLedger {
    @Setter private static CompanyLedger instance;
    private Tile charterTile;
    private Company charterComp;

    public static CompanyLedger getInstance() {
        if (instance == null) {
            instance = new CompanyLedger();
        }
        return instance;

    }
}
