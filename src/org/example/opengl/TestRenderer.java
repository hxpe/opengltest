package org.example.opengl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class TestRenderer implements GLSurfaceView.Renderer {
	private static final String TAG = "TestRenderer";
	private final Context context;
	FloatBuffer verticesBuffer;
	private final int VERTEX_SIZE = (2 + 4) * 4;
	
	TestRenderer(Context context) {
	    this.context = context;
	}
	
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3 * 6 * 4); // 3:顶点数 6:维数2 + 颜色数4 4:一个float型占4个字节
		byteBuffer.order(ByteOrder.nativeOrder());
		verticesBuffer = byteBuffer.asFloatBuffer();
		verticesBuffer.put(new float[] { 0, 0, 1, 0, 0, 1, 320, 0, 0, 1, 0, 1,
				160, 480, 0, 0, 1, 1 });
		verticesBuffer.flip();
		
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	public void onDrawFrame(GL10 gl) {
		gl.glViewport(0, 0, 320, 480);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(0, 320, 0, 480, 1, -1);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		verticesBuffer.position(0);
		gl.glVertexPointer(2, GL10.GL_FLOAT, VERTEX_SIZE, verticesBuffer);
		verticesBuffer.position(2);
		gl.glColorPointer(4, GL10.GL_FLOAT, VERTEX_SIZE, verticesBuffer);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);  
	}

}
