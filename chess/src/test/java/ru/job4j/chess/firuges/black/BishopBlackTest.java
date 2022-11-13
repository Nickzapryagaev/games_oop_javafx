package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class BishopBlackTest {
    @Test
    public void whenPositionCorrect() {
        BishopBlack bb = new BishopBlack(Cell.C8);
        Cell result = bb.position();
        Cell expected = Cell.findBy(2, 0);
        assertThat(result).isEqualByComparingTo(expected);
    }

    @Test
    public void whenCopyCorrect() {
        BishopBlack bb = new BishopBlack(Cell.C8);
        Figure bbNewDest = bb.copy(Cell.H3);
        Cell result = bbNewDest.position();
        Cell expected = Cell.findBy(7, 5);
        assertThat(result).isEqualByComparingTo(expected);
    }

    @Test
    public void whenWayCorrect() {
        BishopBlack bb = new BishopBlack(Cell.C8);
        Figure bbNewDest = bb.copy(Cell.G4);
        Cell newPosition = bbNewDest.position();
        Cell[] result = bb.way(newPosition);
        Cell[] expected = new Cell[]{Cell.D7, Cell.E6, Cell.F5, Cell.G4};
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenWayIncorrect() {
        BishopBlack bb = new BishopBlack(Cell.C8);
        Figure bbNewDest = bb.copy(Cell.G4);
        Cell newPosition = bbNewDest.position();
        Cell[] result = bb.way(newPosition);
        Cell[] expected = new Cell[]{Cell.D7, Cell.E6, Cell.F4, Cell.G4};
        assertThat(result).isNotEqualTo(expected);
    }

    @Test
    public void whenDiagonalException() {
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> {
                    new BishopBlack(Cell.C8).way(Cell.G5);
                });
        String result = String.format("Could not move by diagonal from %s to %s", Cell.C8, Cell.G5);
        assertThat(exception.getMessage()).isEqualTo(result);
    }
}
