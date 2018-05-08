package com.bizleap.training.assignmentThree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MonthMapGenerator {
	private Map<Integer, Object> monthMap = new HashMap<Integer, Object>();
	List<Integer> yearList = new ArrayList<Integer>();

	private class Month {
		private String name;
		private int numberOfDays, year;

		public Month(String name, int numberOfDays, int year) {
			this.name = name;
			this.numberOfDays = numberOfDays;
			this.year = year;
		}

		public String toString() {
			return this.name + "/" + this.year + " ";
		}
	}

	private boolean isValid(int year) {
		return year >= 0;
	}

	private boolean isValid(int fromYear, int toYear) {
		return !(fromYear <= 0 || toYear <= 0 || fromYear > toYear);
	}

	public boolean isLeapYear(int year) {
		return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);
	}

	private List<Month> createMonthsForYear(int fromYear) {
		List<Month> monthList = new ArrayList<Month>();

		monthList.add(new Month("January", 31, fromYear));

		if (isLeapYear(fromYear))
			monthList.add(new Month("February", 29, fromYear));
		else
			monthList.add(new Month("February", 28, fromYear));

		monthList.add(new Month("March", 31, fromYear));
		monthList.add(new Month("April", 30, fromYear));
		monthList.add(new Month("May", 31, fromYear));
		monthList.add(new Month("June", 30, fromYear));
		monthList.add(new Month("July", 31, fromYear));
		monthList.add(new Month("August", 31, fromYear));
		monthList.add(new Month("September", 30, fromYear));
		monthList.add(new Month("October", 31, fromYear));
		monthList.add(new Month("November", 30, fromYear));
		monthList.add(new Month("December", 31, fromYear));

		return monthList;
	}

	public Map<Integer, Object> createMonthMap(int fromYear, int toYear) {
		if (isValid(fromYear, toYear)) {
			for (int year = fromYear; year <= toYear; year++) {
				createMonthMap(year);
				addYearToList(year);
			}
		} else {
			monthMap.put(fromYear, "Range or years are invalid");
			monthMap.put(toYear, "Range or years are invalid");
		}
		return monthMap;
	}

	public Map<Integer, Object> createMonthMap(int year) {
		if (isValid(year)) {
			for (Month month : createMonthsForYear(year)) {
				addMonthToMap(month);
			}
		} else {
			monthMap.put(year, "Year is invalid");
		}
		return monthMap;
	}

	private void addMonthToMap(Month month) {
		List<Month> monthList = (List<Month>) monthMap.get(month.numberOfDays);
		if (monthList != null)
			monthList.add(month);
		else {
			monthList = new ArrayList<Month>();
			monthList.add(month);
			monthMap.put(month.numberOfDays, monthList);
		}
	}

	private void addYearToList(int year) {
		yearList.add(year);
	}

	public void prettyPrint() {
		for (int key : monthMap.keySet()) {
			System.out.println("For " + key + " Days");
			for (Month month : (List<Month>) monthMap.get(key)) {
				System.out.println(month);
			}
			System.out.println();
		}
	}

	private boolean hasPrintableMonth(int year, List<Month> monthList) {
		for (Month month : monthList) {
			if (month.year == year)
				return true;
		}
		return false;
	}

	public void prettyPrint2() {

		for (int key : monthMap.keySet()) {
			System.out.println("For the month(s) ending with " + key + " Days");
			for (int year : yearList) {
				List<Month> monthList = (List<Month>) monthMap.get(key);
				if (hasPrintableMonth(year, monthList)) {
					for (Month month : monthList) {
						if (month.year == year) {
							System.out.print(month);
						}
					}
					System.out.println();
				}
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		MonthMapGenerator monthMapGenerator = new MonthMapGenerator();
		monthMapGenerator.createMonthMap(2000, 2003);
		// monthMapGenerator.prettyPrint();
		monthMapGenerator.prettyPrint2();
	}
}
