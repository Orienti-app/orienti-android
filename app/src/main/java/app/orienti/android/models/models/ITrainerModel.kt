package app.orienti.android.models.models

import sk.backbone.parent.models.IModel

interface ITrainerModel : IModel<ModelsProvider> {

    fun createTraining(name: String){

    }

    fun createTrack(name: String){

    }

    fun createControlPoint(name: String){

    }

    fun addRunToTraining(){

    }

    fun addControlPointToTrack(){

    }

    fun removeTraining(){

    }

    fun removeTrack(){

    }

    fun removeRunFromTraining(){

    }

    fun removeControlPoint(){

    }
}