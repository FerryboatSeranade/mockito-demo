package com.wangwenjun.mockito.lesson06_spying;

import com.wangwenjun.mockito.quickstart.StubbingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {

    @Test
    public void testSpy(){
        List<String> realList = new ArrayList<>();
        List<String> list =spy(realList);
        list.add("Mockito");
        list.add("PowerMock");
        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMock"));
        assertThat(list.isEmpty(), equalTo(false));
        assertThat(list.size(), equalTo(2));

        when(list.isEmpty()).thenReturn(true);
        when(list.size()).thenReturn(0);
        assertThat(list.get(0), equalTo("Mockito"));
        assertThat(list.get(1), equalTo("PowerMock"));
        assertThat(list.isEmpty(), equalTo(true));
        assertThat(list.size(), equalTo(0));

    }

}
