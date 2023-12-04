package com.litongjava.tio.boot.djl.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.Classifications;
import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.types.Shape;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.translate.NoBatchifyTranslator;
import ai.djl.translate.TranslateException;
import ai.djl.translate.TranslatorContext;

public class IrisflowerService {
  String modelUrl = "https://mlrepo.djl.ai/model/tabular/softmax_regression/ai/djl/onnxruntime/iris_flowers/0.0.1/iris_flowers.zip";
  private ZooModel<Irisflower, Classifications> model = null;

  public IrisflowerService() {
    Criteria<Irisflower, Classifications> criteria = Criteria.builder()
        // types
        .setTypes(Irisflower.class, Classifications.class).optModelUrls(modelUrl)
        // treaslator
        .optTranslator(new MyTranslator())
        // 默认使用 OnnxRuntime 引擎
        .optEngine("OnnxRuntime").build();
    try {
      model = criteria.loadModel();
    } catch (ModelNotFoundException e) {
      e.printStackTrace();
    } catch (MalformedModelException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static class Irisflower {
    public float sepalLength;
    public float sepalWidth;
    public float petalLength;
    public float petalWidth;

    public Irisflower(float sepalLength, float sepalWidth, float petalLength, float petalWidth) {
      this.sepalLength = sepalLength;
      this.sepalWidth = sepalWidth;
      this.petalLength = petalLength;
      this.petalWidth = petalWidth;
    }
  }

  public static class MyTranslator implements NoBatchifyTranslator<Irisflower, Classifications> {

    private final List<String> synset;

    public MyTranslator() {
      // species name
      synset = Arrays.asList("setosa", "versicolor", "virginica");
    }

    @Override
    public NDList processInput(TranslatorContext ctx, Irisflower input) {
      float[] data = { input.sepalLength, input.sepalWidth, input.petalLength, input.petalWidth };
      NDArray array = ctx.getNDManager().create(data, new Shape(1, 4));
      return new NDList(array);
    }

    @Override
    public Classifications processOutput(TranslatorContext ctx, NDList list) {
      float[] data = list.get(1).toFloatArray();
      List<Double> probabilities = new ArrayList<>(data.length);
      for (float f : data) {
        probabilities.add((double) f);
      }
      return new Classifications(synset, probabilities);
    }
  }

  public Classifications index() {

    Predictor<Irisflower, Classifications> predictor = model.newPredictor();
    Irisflower info = new Irisflower(1.0f, 2.0f, 3.0f, 4.0f);
    Classifications result = null;
    try {
      result = predictor.predict(info);
    } catch (TranslateException e) {
      e.printStackTrace();
    }
    return result;
  }
}
