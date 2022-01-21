package com.ngallazzi.myapplication

class MainActivityController {
    private var s: PrintingState = PrintingState.UNDEFINED

    fun setPrintingState(newState: PrintingState) {
        s = newState
    }

    fun getPrintingState(): PrintingState {
        return s;
    }
}