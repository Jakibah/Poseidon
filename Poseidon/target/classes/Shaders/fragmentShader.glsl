#version 400 core

in vec2 pass_textureCoords;
in vec3 pass_sunLight;

out vec4 out_Colour;

uniform sampler2D textureSampler;
uniform vec3 sunLight;
uniform vec3 light;
uniform float isLighted;

void main(void){



	vec4 originalColor= texture(textureSampler, pass_textureCoords);;
	vec4 AlphaTest = originalColor;
	if(AlphaTest.a < 0.5){
	discard;
	}
	if(isLighted == 1){
	AlphaTest.x = max(AlphaTest.x - sunLight.x, 0);
	AlphaTest.y = max(AlphaTest.y - sunLight.y, 0);
	AlphaTest.z = max(AlphaTest.z - sunLight.z, 0);
	AlphaTest.x = min(AlphaTest.x + light.x, originalColor.x);
	AlphaTest.y = min(AlphaTest.y + light.y, originalColor.y);
	AlphaTest.z = min(AlphaTest.z + light.z, originalColor.z);
	}
	

	out_Colour = AlphaTest;
}
