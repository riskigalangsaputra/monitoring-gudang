package com.nusamandiri.monitoringgudang.specification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author galang
 */
public class CustomSpecification<T> implements Specification<T> {

    private final SearchCriteria criteria;

    private final SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private final DateTimeFormatter formatLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter formatLocalDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CustomSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {

        switch (criteria.getOperation().toUpperCase()) {
            case "LIKE": {
                if (criteria.getKey().contains("/")) {

                    String[] variables = criteria.getKey().split("/");
                    List<Predicate> listOfPredicate = new ArrayList<>();

                    for (int i = 0; i < criteria.getKey().split("/").length; i++) {

                        if (variables[i].contains("-")) {

                            String[] var = variables[i].split("-");
                            switch (var.length) {
                                case 2:{
                                    Predicate predicate = builder.like(
                                            builder.lower(
                                                    root.get(var[0]).get(var[1])), "%" + criteria.getValue().toString().toLowerCase() + "%");
                                    listOfPredicate.add(predicate);
                                    break;
                                }
                                case 3:{
                                    Predicate predicate = builder.like(
                                            builder.lower(
                                                    root.get(var[0]).get(var[1]).get(var[2])), "%" + criteria.getValue().toString().toLowerCase() + "%");
                                    listOfPredicate.add(predicate);
                                    break;
                                }
                            }

                        } else {
                            Predicate predicate = builder.like(
                                    builder.lower(
                                            root.get(variables[i])), "%" + criteria.getValue().toString().toLowerCase() + "%");
                            listOfPredicate.add(predicate);
                        }

                    }

                    return builder.or(listOfPredicate.toArray(new Predicate[listOfPredicate.size()]));

                } else {
                    if (criteria.getKey().contains("-")) {
                        switch (criteria.getKey().split("-").length) {
                            case 2: {
                                return builder.like(
                                        builder.lower(
                                                root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1])),
                                        "%" + criteria.getValue().toString().toLowerCase() + "%");
                            }
                            case 3: {
                                return builder.like(
                                        builder.lower(
                                                root.get(criteria.getKey().split("-")[0])
                                                        .get(criteria.getKey().split("-")[1])
                                                        .get(criteria.getKey().split("-")[2])),
                                        "%" + criteria.getValue().toString().toLowerCase() + "%");
                            }
                        }
                    } else {
                        if (root.get(criteria.getKey()).getJavaType() == String.class) {
                            return builder.like(
                                    builder.lower(
                                            root.get(criteria.getKey())),
                                    "%" + criteria.getValue().toString().toLowerCase() + "%");
                        }
                    }
                }
                break;
            }
            case "EQUAL": {
                if (criteria.getKey().contains("-")) {
                    switch (criteria.getKey().split("-").length) {
                        case 2: {
                            return builder.equal(root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1]), criteria.getValue());
                        }
                        case 3: {
                            return builder.equal(root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1]).get(criteria.getKey().split("-")[2]), criteria.getValue());
                        }
                        case 4: {
                            return builder.equal(root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1]).get(criteria.getKey().split("-")[2]).get(criteria.getKey().split("-")[3]), criteria.getValue());
                        }
                        case 5: {
                            return builder.equal(root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1]).get(criteria.getKey().split("-")[2]).get(criteria.getKey().split("-")[3]).get(criteria.getKey().split("-")[4]), criteria.getValue());
                        }
                        case 6: {
                            return builder.equal(root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1]).get(criteria.getKey().split("-")[2]).get(criteria.getKey().split("-")[3]).get(criteria.getKey().split("-")[4]).get(criteria.getKey().split("-")[5]), criteria.getValue());
                        }
                    }

                } else {
                    return builder.equal(root.get(criteria.getKey()), criteria.getValue());
                }
            }
            case "DATE_GREATER_THAN": {
                return builder.greaterThan(root.get(criteria.getKey()), (Date) criteria.getValue());
            }
            case "DATE_LESS_THAN": {
                return builder.lessThan(root.get(criteria.getKey()), (Date) criteria.getValue());
            }
            case "DATE_GREATER_THAN_EQUAL": {
                return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Date) criteria.getValue());
            }
            case "DATE_LESS_THAN_EQUAL": {
                return builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Date) criteria.getValue());
            }
            case "EQUAL_OR_NULL": {
                if (criteria.getKey().contains("-")) {
                    Predicate p1 = builder.equal(root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1]), criteria.getValue());
                    Predicate p2 = builder.isNull(root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1]));
                    return builder.or(p1, p2);
                } else {
                    Predicate p1 = builder.equal(root.get(criteria.getKey()), criteria.getValue());
                    Predicate p2 = builder.isNull(root.get(criteria.getKey()));
                    return builder.or(p1, p2);
                }
            }
            case "IS_NULL_MAPPED_BY": {
                Join join = root.join(criteria.getKey(), JoinType.LEFT);
                return join.isNull();
            }
            case "LESSER_THAN_OTHER_COLUMN": {
                String col1 = criteria.getKey().split("\\|")[0];
                String col2 = criteria.getKey().split("\\|")[1];
                if (criteria.getKey().contains("-")) {
                    if (col1.contains("-") && col2.contains("")) {
                        return builder.lessThan(root.get(col1.split("-")[0]).get(col1.split("-")[1]), root.get(col2.split("-")[0]).get(col2.split("-")[1]));
                    } else {
                        if (col1.contains("-")) {
                            return builder.lessThan(root.get(col1.split("-")[0]).get(col1.split("-")[1]), root.get(col2));
                        }
                        if (col2.contains("-")) {
                            return builder.lessThan(root.get(col1), root.get(col2.split("-")[0]).get(col2.split("-")[1]));
                        }
                    }
                } else {
                    return builder.equal(root.get(col1), root.get(col2));
                }
            }
            case "IS_NULL": {
                if (criteria.getKey().contains("-")) {
                    return builder.isNull(root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1]));
                } else {
                    return builder.isNull(root.get(criteria.getKey()));
                }
            }
            case "NOT_NULL": {
                if (criteria.getKey().contains("-")) {
                    return builder.isNotNull(root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1]));
                } else {
                    return builder.isNotNull(root.get(criteria.getKey()));
                }
            }
            case "NOT_EQUAL": {
                if (criteria.getKey().contains("-")) {
                    return builder.notEqual(root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1]), criteria.getValue());
                } else {
                    return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
                }
            }
            case "BETWEEN": {
                String[] dateStr = criteria.getValue().toString().split(" - ");
                try {
                    Date end = formatDate.parse(dateStr[1]);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(end);
                    cal.set(Calendar.HOUR_OF_DAY, 23);
                    cal.set(Calendar.MINUTE, 59);
                    cal.set(Calendar.SECOND, 59);
                    end = cal.getTime();
                    logger.debug("SEARCH " + criteria.getKey() + " BETWEEN DATE " + formatDate.parse(dateStr[0]) + " and " + end);
                    return builder.between(root.get(criteria.getKey()),
                            formatDate.parse(dateStr[0]),
                            end);
                } catch (ParseException ex) {
                    logger.error("ERROR PARSE SPECIFICATION BETWEEN DATE");
                }
                break;
            }
            case "LOCAL_DATE_BETWEEN": {
                String[] dateStr = criteria.getValue().toString().split(" - ");
                LocalDate dateStart = LocalDate.parse(dateStr[0],formatLocalDate);
                LocalDate dateEnd = LocalDate.parse(dateStr[1],formatLocalDate);

                return builder.between(root.get(criteria.getKey()), dateStart, dateEnd);
            } case "LOCAL_DATE_TIME_BETWEEN": {
                String[] dateStr = criteria.getValue().toString().split(" - ");

                LocalDateTime dateStart = LocalDateTime.parse(dateStr[0],formatLocalDateTime);
                LocalDateTime dateEnd = LocalDateTime.parse(dateStr[1],formatLocalDateTime);

                return builder.between(root.get(criteria.getKey()), dateStart, dateEnd);
            }
            case "IN": {
                if (criteria.getKey().contains("-")) {
                    Expression<String> parentExpression = root.get(criteria.getKey().split("-")[0]).get(criteria.getKey().split("-")[1]);
                    return parentExpression.in(criteria.getValue());
                } else {
                    return root.get(criteria.getKey()).in(criteria.getValue());
                }
            }
            case "MONTH": {
                return builder.equal(builder.function("MONTH", Integer.class, root.get(criteria.getKey())), criteria.getValue());
            }
            case "YEAR": {
                return builder.equal(builder.function("YEAR", Integer.class, root.get(criteria.getKey())), criteria.getValue());
            }
            case "EQUAL_DATE": {
                try {
                    Date date = formatDate.parse(criteria.getValue().toString());
                    return builder.equal(root.get(criteria.getKey()), date);
                } catch (ParseException e) {
                    logger.error("ERROR PARSE SPECIFICATION EQUAL DATE");
                }
            }
            case "DAY_MONTH_YEAR": {
                int day = Integer.valueOf(criteria.getValue().toString().split("/")[0]);
                int month = Integer.valueOf(criteria.getValue().toString().split("/")[1]);
                int year = Integer.valueOf(criteria.getValue().toString().split("/")[2]);

                Predicate eDate = builder.equal(builder.function("DAY", Integer.class, root.get(criteria.getKey())), day);
                Predicate eMonth = builder.equal(builder.function("MONTH", Integer.class, root.get(criteria.getKey())), month);
                Predicate eYear = builder.equal(builder.function("YEAR", Integer.class, root.get(criteria.getKey())), year);
                return builder.and(eDate, eMonth, eYear);
            }
            case "DATE_MONTH_YEAR": {
                //query based month of the date
                String date = formatDate.format(criteria.getValue());
                int month = Integer.valueOf(date.split("/")[1]);
                int year = Integer.valueOf(date.split("/")[2]);

                Predicate eMonth = builder.equal(builder.function("MONTH", Integer.class, root.get(criteria.getKey())), month);
                Predicate eYear = builder.equal(builder.function("YEAR", Integer.class, root.get(criteria.getKey())), year);
                return builder.and(eMonth, eYear);
            }
            case "LAST_30_DAYS": {
                Date start = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(start);
                cal.set(Calendar.HOUR_OF_DAY, 23);
                cal.set(Calendar.MINUTE, 59);
                cal.set(Calendar.SECOND, 59);
                Date endDate = cal.getTime();

                cal.add(Calendar.DATE,-30);

                cal.set(Calendar.HOUR_OF_DAY, 0);
                cal.set(Calendar.MINUTE, 0);
                cal.set(Calendar.SECOND, 0);
                Date startDate = cal.getTime();

                logger.debug("SEARCH " + criteria.getKey() + " BETWEEN DATE " + formatDate.format(startDate) + " and " + formatDate.format(endDate));

                return builder.between(root.get(criteria.getKey()), startDate, endDate);
            }
            case "LAST_6_MONTH": {
                Date date = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.MONTH,-5);

                String sDate = formatDate.format(date);
                int month = Integer.valueOf(sDate.split("/")[1]);
                int year = Integer.valueOf(sDate.split("/")[2]);

//                int endMonth = calendar.

                Predicate eMonth = builder.between(builder.function("MONTH", Integer.class, root.get(criteria.getKey())), calendar.get(Calendar.MONTH),month);
                Predicate eYear = builder.equal(builder.function("YEAR", Integer.class, root.get(criteria.getKey())), year);
                return builder.and(eMonth, eYear);
            }
            case "DATE_DAY_MONTH_YEAR": {
                String sDate = formatDate.format(criteria.getValue());

                int day = Integer.valueOf(sDate.split("/")[0]);
                int month = Integer.valueOf(sDate.split("/")[1]);
                int year = Integer.valueOf(sDate.split("/")[2]);

                Predicate eDate = builder.equal(builder.function("DAY", Integer.class, root.get(criteria.getKey())), day);
                Predicate eMonth = builder.equal(builder.function("MONTH", Integer.class, root.get(criteria.getKey())), month);
                Predicate eYear = builder.equal(builder.function("YEAR", Integer.class, root.get(criteria.getKey())), year);
                return builder.and(eDate, eMonth, eYear);
            }
            case "LOCAL_DATE_BEFORE":{
                return builder.lessThan(root.get(criteria.getKey()), (LocalDate) criteria.getValue());
            }
            default: {
                logger.info("handling for this operation [" + criteria.getOperation() + "] is not found!");
//                not found
            }
        }
        return null;
    }
}

