package com.appcenttodolist.todolist.core.Business;

import com.appcenttodolist.todolist.core.Results.Result;
import com.appcenttodolist.todolist.core.Results.SuccessResult;

public class BusinessEngine {

    public static Result run(Result... results) {
        for (Result result : results) {
            if (!result.isSuccess()){
                return result;
            }
        }
        return new SuccessResult();
    }
}
