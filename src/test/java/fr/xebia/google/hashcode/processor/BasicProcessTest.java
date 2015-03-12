package fr.xebia.google.hashcode.processor;

import com.google.common.collect.Lists;
import fr.xebia.google.hashcode.model.Server;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicProcessTest {

    private BasicProcess basicProcess;

    @Before
    public void setUp() throws Exception {
        this.basicProcess = new BasicProcess();
    }

    @Test
    public void should_sort_server_by_size() {
        // Given
        Server s1 = new Server(0, 0, 10);
        Server s2 = new Server(0, 0, 20);
        Server s3 = new Server(0, 0, 15);

        List<Server> servers = Lists.newArrayList(s1, s2, s3);

        // When
        List<Server> sortedServer = basicProcess.sortServerBySize(servers);

        // Then
        assertThat(sortedServer).containsExactly(s2, s3, s1);
    }


}