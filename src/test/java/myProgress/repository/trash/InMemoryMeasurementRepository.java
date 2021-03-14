//package myProgress.repository.inmemory;
//
//import myProgress.model.Measurement;
//import myProgress.repository.MeasurementRepository;
//import myProgress.util.MeasurementsUtil;
//import myProgress.util.Util;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.function.Predicate;
//import java.util.stream.Collectors;
//
//import static myProgress.UserTestData.*;
//
//
//@Repository
//public class InMemoryMeasurementRepository implements MeasurementRepository {
//    private static final Logger log = LoggerFactory.getLogger(InMemoryMeasurementRepository.class);
//
//
//    private final Map<Integer, InMemoryBaseRepository<Measurement>> measurementsMap = new ConcurrentHashMap<>();
//
//    {
//        MeasurementsUtil.measurements.forEach(item -> save(item, USER_ID));
//        save(new Measurement(LocalDate.now().minusDays(5), 77.7, 78d, 106d), ADMIN_ID);
//        save(new Measurement(LocalDate.now().minusDays(4), 77.8, 78d, 106d), ADMIN_ID);
//    }
//
//    /**
//     * gcomputeIfAbsent checks the presence of InMemoryBaseRepository<Measurement> in measurementsMap
//     * and adds new if absent
//     **/
//    @Override
//    public Measurement save(Measurement measurement, int userId) {
//        InMemoryBaseRepository<Measurement> meals =
//                measurementsMap.computeIfAbsent(userId, uid -> new InMemoryBaseRepository<>());
//        return meals.save(measurement);
//    }
//
//    @PostConstruct
//    public void postConstruct() {
//        log.info("+++ PostConstruct");
//    }
//
//    @PreDestroy
//    public void preDestroy() {
//        log.info("+++ PreDestroy");
//    }
//
//    @Override
//    public boolean delete(int id, int userId) {
//        InMemoryBaseRepository<Measurement> measurements = measurementsMap.get(userId);
//        return measurements != null && measurements.delete(id);
//    }
//
//    @Override
//    public Measurement get(int id, int userId) {
//        InMemoryBaseRepository<Measurement> measurements = measurementsMap.get(userId);
//        return measurements == null ? null : measurements.get(id);
//    }
//
//    @Override
//    public Measurement get(int id, int userId, int userProgressId) {
//        InMemoryBaseRepository<Measurement> measurements = measurementsMap.get(userProgressId);
//        return measurements == null ? null : measurements.get(id);
//
//    }
//
//    @Override
//    public List<Measurement> getAll(int userId) {
//        return filterByPredicate(userId, measurement -> true);
//    }
//
//    @Override
//    public List<Measurement> getAll(int userId, int userProgressId) {
//        return filterByPredicate(userProgressId, measurement -> true);
//    }
//
//    @Override
//    public List<Measurement> getBetween(LocalDate startDate, LocalDate endDate, int userId) {
//        return filterByPredicate(userId, measurement -> Util.isBetween(measurement.getDate(), startDate, endDate));
//    }
//
//    @Override
//    public List<Measurement> getBetween(LocalDate startDate, LocalDate endDate, int userId, int userProgressId) {
//        return filterByPredicate(userProgressId, m -> Util.isBetween(m.getDate(), startDate, endDate));
//    }
//
//    private List<Measurement> filterByPredicate(int userId, Predicate<Measurement> filter) {
//        InMemoryBaseRepository<Measurement> measurements = measurementsMap.get(userId);
//        return measurements == null ? Collections.emptyList() :
//                measurements.getCollection().stream()
//                        .filter(filter)
//                        .sorted(Comparator.comparing(Measurement::getDate).reversed())
//                        .collect(Collectors.toList());
//    }
//}