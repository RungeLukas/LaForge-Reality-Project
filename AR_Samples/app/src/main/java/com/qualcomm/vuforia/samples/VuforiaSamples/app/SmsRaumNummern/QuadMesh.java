package com.qualcomm.vuforia.samples.VuforiaSamples.app.SmsRaumNummern;

import com.qualcomm.vuforia.samples.SampleApplication.utils.MeshObject;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by kr on 28.08.2015.
 */
public class QuadMesh extends MeshObject {

    // SMS - Änderung - kr - mVertices where changed from 0.5 to 0.05
    private final float[] mVertices = new float[] {
            -0.15f, -0.05f, 10.2f, //bottom-left corner
            -0.05f, -0.05f, 0.0f, //bottom-right corner
            -0.05f, 0.05f, 0.0f, //top-right corner
            -0.15f, 0.05f, 10.2f //top-left corner
    };
    private final float[] mNormals = new float[] {
            0.0f, 0.0f, 1.0f, //normal at bottom-left corner
            0.0f, 0.0f, 1.0f, //normal at bottom-right corner
            0.0f, 0.0f, 1.0f, //normal at top-right corner
            0.0f, 0.0f, 1.0f //normal at top-left corner
    };
    private final float[] mTexCoords = new float[] {
            0.0f, 0.0f, //tex-coords at bottom-left corner
            1.0f, 0.0f, //tex-coords at bottom-right corner
            1.0f, 1.0f, //tex-coords at top-right corner
            0.0f, 1.0f //tex-coords at top-left corner
    };
    private final short[] mIndices = new short[] {
            0,1,2, //triangle 1
            2,3,0 // triangle 2
    };

    public final Buffer vertices;
    public final Buffer normals;
    public final Buffer texCoords;
    public final Buffer indices;

    private int indicesNumber = mVertices.length;//3; //3 coords per vertex;
    private int verticesNumber = mIndices.length;

    /*
    public final int numVertices = mVertices.length/3; //3 coords per vertex
    public final int numIndices = mIndices.length;
    */
    public QuadMesh() {
    // init vertex buffers
        vertices = fillBuffer(mVertices);
        normals = fillBuffer(mNormals);
        texCoords = fillBuffer(mTexCoords);
        indices = fillBuffer(mIndices);
    }
    protected Buffer fillBuffer(float[] array) {
        final int sizeOfFloat = 4;//1 float = 4 bytes
        ByteBuffer bb = ByteBuffer.allocateDirect(sizeOfFloat * array.length);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        for (float f : array) {
            bb.putFloat(f);
        }
        bb.rewind();
        return bb;
    }
    protected Buffer fillBuffer(short[] array) {
        final int sizeOfShort = 2;//1 short = 2 bytes
        ByteBuffer bb = ByteBuffer.allocateDirect(sizeOfShort * array.length);
        bb.order(ByteOrder.LITTLE_ENDIAN);
        for (short s : array) {
            bb.putShort(s);
        }
        bb.rewind();
        return bb;
    }

    //@Override
    public Buffer getBuffer(BUFFER_TYPE bufferType) {
        Buffer result = null;
        switch (bufferType)
        {
            case BUFFER_TYPE_VERTEX:
                result = vertices;
                break;
            case BUFFER_TYPE_TEXTURE_COORD:
                result = texCoords;  break;
            case BUFFER_TYPE_NORMALS:
                result = normals;
                break;
            case BUFFER_TYPE_INDICES:
                result = indices;
            default:
                break;

        }
        return result;


    }

    @Override
    public int getNumObjectVertex() {

        return verticesNumber;
    }

    @Override
    public int getNumObjectIndex() {

        return indicesNumber;
    }
}
