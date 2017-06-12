package com.interview.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String input = request.getParameter("array").toString().trim();
		int sortType = Integer.valueOf(request.getParameter("sortType"));
		String[] sArr = input.split(",");
		int[] arr = new int[sArr.length];
		String typeName = "";
		if (sortType == 1){
			typeName = "升序";
		}
		if (sortType == 0) {
			typeName = "降序";
		}
		
		for (int i = 0; i < sArr.length; i++) {
			arr[i] = Integer.valueOf(sArr[i]);
		}
		
		bubbleSort(arr, sortType);
		
		request.setAttribute("result", Arrays.toString(arr));
		request.setAttribute("sortType", typeName);
		
		request.getRequestDispatcher("../result.jsp").forward(request, response);
		
	}
	
	private void bubbleSort(int[] arr, int sortType) {
		int n = arr.length;
		boolean stored = false;
		while (!stored) {
			stored = true;
			for (int i = 1; i < n; i++) {
				if (sortType == 1) {
					if (arr[i - 1] > arr[i]){
						swap(arr, i - 1, i);
						stored = false;
					}
				}else {
					if (arr[i - 1] < arr[i]){
						swap(arr, i - 1, i);
						stored = false;
					}
				}
			}
		}
	}
	
	private void swap(int[] arr, int i, int j) {
		arr[i] = arr[i] ^ arr[j];
		arr[j] = arr[i] ^ arr[j];
		arr[i] = arr[i] ^ arr[j];
	}

}
