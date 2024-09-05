package com.projects.xmen_adn.domain.logic;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.projects.xmen_adn.domain.model.constants.PersonaConstant;
import com.projects.xmen_adn.domain.port.MutantePort;
import com.projects.xmen_adn.infrastructure.adapter.exception.ApiException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MutanteLogic implements MutantePort {

    private static final int REQUIRED_SEQUENCE_LENGTH = 4;

    @Override
    public boolean isMutante(String[] dna) {
        log.info("[isMutante(save)] -> Detectando ADN ");

        try {
            if (dna == null || dna.length == 0) {
                throw new ApiException(HttpStatus.NOT_FOUND,
                        String.format(PersonaConstant.ADN_INVALID));
            }

            int adnSize = dna.length;
            int sequenceCount = 0;

            for (int i = 0; i < adnSize; i++) {
                sequenceCount += countSequences(dna[i]);
                sequenceCount += countSequences(getColumn(dna, i));
            }

            sequenceCount += countDiagonalSequences(dna, adnSize);
            sequenceCount += countDiagonalSequences(reverseArray(dna), adnSize);

            return sequenceCount > 1;
        } catch (Exception e) {
            log.error("Error Detectando ADN ", e.getMessage());

            return false;
        }
    }

    private int countSequences(String sequence) {
        int count = 0;
        int consecutiveCount = 1;

        for (int i = 1; i < sequence.length(); i++) {
            if (sequence.charAt(i) == sequence.charAt(i - 1)) {
                consecutiveCount++;
                if (consecutiveCount == REQUIRED_SEQUENCE_LENGTH) {
                    count++;
                }
            } else {
                consecutiveCount = 1;
            }
        }
        return count;
    }

    private String getColumn(String[] dna, int columnIndex) {
        StringBuilder column = new StringBuilder();
        for (String row : dna) {
            column.append(row.charAt(columnIndex));
        }
        return column.toString();
    }

    private int countDiagonalSequences(String[] dna, int size) {
        int count = 0;

        for (int i = 0; i < size; i++) {
            count += countDiagonalFrom(dna, i, 0, size);
            if (i > 0) {
                count += countDiagonalFrom(dna, 0, i, size);
            }
        }

        return count;
    }

    private int countDiagonalFrom(String[] dna, int startRow, int startCol, int size) {
        int count = 0;
        int row = startRow;
        int col = startCol;
        int consecutiveCount = 1;

        while (row < size - 1 && col < size - 1) {
            if (dna[row].charAt(col) == dna[row + 1].charAt(col + 1)) {
                consecutiveCount++;
                if (consecutiveCount == REQUIRED_SEQUENCE_LENGTH) {
                    count++;
                }
            } else {
                consecutiveCount = 1;
            }
            row++;
            col++;
        }
        return count;
    }

    private String[] reverseArray(String[] array) {
        String[] reversed = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = new StringBuilder(array[i]).reverse().toString();
        }

        return reversed;
    }
}
