package io.zyd.learnspringboot.topic;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private List<Topic> topics = new ArrayList<>(Arrays.asList(
            new Topic("spring", "Sprint Framework", "SF description"),
            new Topic("Java", "Jasva 8", "Java description"),
            new Topic("JavaScript", "ES6", "JS description")));

    public List<Topic> getAllTopics() {
        return topics;
    }

    public Topic getTopic(String id) {
        Optional<Topic> opt = topics.stream()
                                    .filter(topic -> topic.getId().equals(id))
                                    .findFirst();
        return opt.orElse(new Topic());
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void updateTopic(String id, Topic topic) {
        topics.set(topics.indexOf(getTopic(id)), topic);
    }

    public void deleteTopic(String id) {
        topics.removeIf(t -> t.getId().equals(id));
    }
}
