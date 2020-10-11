package com.wangwenjun.mockito.lesson06_spying;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpyingAnnotationTest {

    @Spy
    private List<String> list =new ArrayList<>();

    @Test
    public void testSpy(){
        list.add("Mockito2");
        list.add("PowerMock2");
        assertThat(list.get(0), equalTo("Mockito2"));
        assertThat(list.get(1), equalTo("PowerMock2"));
        assertThat(list.isEmpty(), equalTo(false));
        assertThat(list.size(), equalTo(2));

        when(list.isEmpty()).thenReturn(true);
        when(list.size()).thenReturn(0);
        assertThat(list.get(0), equalTo("Mockito2"));
        assertThat(list.get(1), equalTo("PowerMock2"));
        assertThat(list.isEmpty(), equalTo(true));
        assertThat(list.size(), equalTo(0));
    }

}
