package com.ngallazzi.refactoringplayground

class MainActivityController {
    private var printingState: PrintingState = PrintingState.UNDEFINED

    fun setPrintingState(newState: PrintingState) {
        printingState = newState
    }

    fun getPrintingState(): PrintingState {
        return printingState;
    }
}