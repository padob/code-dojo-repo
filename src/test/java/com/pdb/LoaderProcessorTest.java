package com.pdb;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.stream.XMLStreamException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoaderProcessorTest {
    private FileWritter fileWritter;
    private LoaderProcessor loaderProcessor;

    @Mock
    private VeryRobustService veryRobustService;

    private static Stream<Arguments> loaderDataProvider() {
        return Stream.of(
                Arguments.of("00-basic-correct-example.xml", 0)
        );
    }

    @ParameterizedTest
    @MethodSource("loaderDataProvider")
    void starter(String fixtureName, int expectedInvocationsNumber)
            throws XMLStreamException {
        //given
        when(veryRobustService.call(anyString())).thenReturn("veryRobustService was called");
        fileWritter = new FileWritter();
        loaderProcessor = new LoaderProcessor(fileWritter, veryRobustService);

        //when
        loaderProcessor.load(fixtureName);

        //then
        assertEquals(expectedInvocationsNumber, fileWritter.getCallsNumber());
        verify(veryRobustService, times(1)).call(anyString());
    }
}