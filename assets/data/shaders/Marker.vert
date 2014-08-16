uniform mat4 u_matrix;                            
attribute vec4 a_position;                       
attribute vec2 a_texCoord0;
attribute vec4 a_color;
varying vec4 v_color;             
varying vec2 v_TextureCoordinates;               

void main() {
    v_color = vec4(1, 1, 1, 1);                         
    v_TextureCoordinates = a_texCoord0; 
    gl_Position = u_matrix * a_position;         
}