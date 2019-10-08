package com.saty.ml;

import org.tensorflow.*;

public class App {

	public Output<Integer> addConstant(Graph g, String name, Object value) {
		try(Tensor<?> t = Tensor.create(value);){
			return g.opBuilder("Const", name)
					.setAttr("dtype", t.dataType())
					.setAttr("value", t)
					.build()
					.output(0);
		}
	}

	public Output<Operation> addAddOperation(Graph g, Output<?>...inputs) {
		return g.opBuilder("AddN", "TheBigAdder").addInputList(inputs).build().output(0);
	} 
}
