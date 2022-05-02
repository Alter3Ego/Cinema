package model.commands;

import model.dao.impl.SessionDaoImpl;
import model.service.impl.SessionServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainPageCommandTest {
    @Spy
    @InjectMocks
    MainPageCommand mainPageCommand;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpSession sessionMock;
    @Mock
    SessionDaoImpl SessionDaoImpl;
    @Mock
    SessionServiceImpl sessionService;
    @Captor
    ArgumentCaptor<String> paginationSort;
    @Captor
    ArgumentCaptor<Integer> start;
    @Captor
    ArgumentCaptor<Integer> end;
    @Captor
    ArgumentCaptor<Integer> limit;


    @ParameterizedTest
    @MethodSource("generateData")
    void paginationTest(Boolean limitPlaces, String sort, Integer previousPage,
                        String expectedPaginationSort, Integer expectedStart,
                        Integer expectedEnd, Integer expectedLimit) {

        Map<String, Object> mapRequestParameter = new HashMap<>();
        mapRequestParameter.put("limitPlaces", limitPlaces);
        mapRequestParameter.put("sort", sort);

        when(request.getSession()).thenReturn(sessionMock);
        Map<String, Object> mapSessionAttribute = new HashMap<>();
        lenient().doAnswer(invocation -> {
            String key = invocation.getArgument(0);
            Object value = invocation.getArgument(1);
            mapSessionAttribute.put(key, value);
            return null;
        }).when(sessionMock).setAttribute(anyString(), any());
        when(request.getParameter(anyString())).
                thenAnswer(i -> mapSessionAttribute.get(i.getArguments()[0]));

        when(request.getSession().getAttribute(anyString())).
                thenAnswer(i -> mapRequestParameter.get(i.getArguments()[0]));

        doNothing().when(mainPageCommand).extracted(any(), anyInt(), any());

        mainPageCommand.pagination(request, previousPage);
        Mockito.verify(sessionService, times(1)).
                getServicesOrderByLimits(paginationSort.capture(), start.capture(), end.capture(), limit.capture());

        assertEquals(expectedPaginationSort, paginationSort.getValue());
        assertEquals(expectedStart, start.getValue());
        assertEquals(expectedEnd, end.getValue());
        assertEquals(expectedLimit, limit.getValue());
    }

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(true, "name", "5",
                        "name", 4, 9, 20),
                Arguments.of(false, "places", "1",
                        "places", 0, 5, 30),
                Arguments.of(false, null, null,
                        "dateTime", 0, 5, 30)
        );
    }

}