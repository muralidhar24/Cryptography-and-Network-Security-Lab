#include<stdio.h>
#include<conio.h>
#include<string.h>
#include<stdio.h>
int main() 
{
	char str[] = "HELLO WORLD";
	char str1[11];
	int i, len;
	len = strlen(str);
	for(i=0; i<len; i++)
	{
		str1[i] = str[i] & 127;
		printf("%c", str1[i]);
	}	
	printf("\n");
	for(i=0; i<len; i++)
	{
		str1[i] = str[i] ^ 127;
		printf("%c", str1[i]);
	}	
	printf("\n");
	for(i=0; i<len; i++)
	{
		str1[i] = str[i] | 127;
		printf("%c", str1[i]);
	}	
	printf("\n");
}
