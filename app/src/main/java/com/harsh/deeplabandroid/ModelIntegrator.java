package com.harsh.deeplabandroid;

import android.content.res.AssetManager;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

public class ModelIntegrator {

    private String MODEL_FILE_CNN = "file:///android_asset/frozen_inference_graph.pb";

    private String INPUT_NODE;
    private String OUTPUT_NODE;

    private TensorFlowInferenceInterface inferenceInterface;

    public void init(AssetManager asset){
        inferenceInterface = new TensorFlowInferenceInterface(asset,MODEL_FILE_CNN);
    }

    public void run(){

    }


}
