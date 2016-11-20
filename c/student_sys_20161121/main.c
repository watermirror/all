#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifndef bool
#define bool int
#endif

// #define OS_WIN
#define OS_UNIX
 
#define MAX_STUDENTS 51
 
// Student data structure.
typedef struct Student {
  char name[20];
  int id;
  int math;
  int chi;
  int eng;
  int sum;
  int avg;
} Student;
 
// Comparing function for Students.
typedef bool (* LessFunction)(const Student*, const Student*);
 
void ClearScreen() {
#if defined(OS_WIN)
  system("cls");
#elif defined(OS_UNIX)
  system("clear");
#endif
}
 
void Pause() {
#if defined(OS_WIN)
  fflush(stdin);
#elif defined(OS_UNIX)
  getchar();
#endif
  getchar();
}
 
// Print a split line.
void PrintSplit() {
  printf("------------------------------------------------\n");
}
 
// Reckon the sum score and the average score of one specific student.
void ReckonScores(Student* student) {
  student->sum = student->math + student->chi + student->eng;
  student->avg = student->sum / 3;
}
 
//
// 5 functions below are for inputting.
//
void InputAnyTypeField(const char* filter,
                       const char* field_name,
                       void* value) {
  fflush(stdin);
  printf("%s: ", field_name);
  if (strcmp(filter, "%s") == 0) {
    gets((char*)value);
  } else {
    scanf(filter, value);
  }
  fflush(stdin);
}
 
void InputStringField(const char* field_name, char* value) {
#if defined(OS_UNIX)
  getchar();
#endif
  InputAnyTypeField("%s", field_name, value);
}
 
void InputIntegerField(const char* field_name, int* value) {
  InputAnyTypeField("%d", field_name, value);
}
 
void InputSingleStudent(Student* student) {
  InputStringField("Name", student->name);
  InputIntegerField("ID", &student->id);
  InputIntegerField("Score of Math", &student->math);
  InputIntegerField("Score of Chinese", &student->chi);
  InputIntegerField("Score of English", &student->eng);
  ReckonScores(student);
  PrintSplit();
}
 
int InputAllStudent(Student* student, int max_allowed) {
  ClearScreen();
  int count = 0;
 
  // Get the legal count.
  for (; printf("How many strudents you want to input? "),
         scanf("%d", &count),
         count > max_allowed || count < 0;
       printf("Cannot more than %d students or less than 0.\n", max_allowed));
  int count_down = count;
 
  // Input all students' information.
  while (count_down--) {
    InputSingleStudent(student++);
  }
  printf("DONE: %d students have been inputted.\n", count);
  Pause();
  return count;
}
 
// Display all students' informations.
void DisplayAllStudents(const Student* student, int count) {
  ClearScreen();
  printf("ID   | Name                | MAT | CH  | EN  | SUM | AVG\n");
  while (count--) {
    printf("%04d | %-19s | %3d | %3d | %3d | %3d | %3d\n",
           student->id, student->name, student->math, student->chi,
           student->eng, student->sum, student->avg);
    student++;
  }
  PrintSplit();
  printf("DONE: Display students's informatons.\n");
  Pause();
  return;
}
 
//
// 2 functions below are the comparing functions for sorting.
//
bool LessBySum(const Student* a, const Student* b) {
  return a->sum < b->sum;
}
 
bool LessByAvg(const Student* a, const Student* b) {
  return a->avg < b->avg;
}
 
int ShowSortingMenu() {
  ClearScreen();
  int selection = 0;
  for (; printf("Sort by what? SUM(1) AVG(2) Go Back(0): "),
         scanf("%d", &selection),
         selection < 0 || selection > 2;
       printf("Ilegal input!\n"));
  return selection;
}
 
void SortAndDisplayAllStudents(LessFunction less_function,
                               Student* student,
                               int count) {
  for (int i = 0; i < count - 1; i++) {
    for (int j = i + 1; j < count; j++) {
      if ((*less_function)(student + i, student + j)) {
        Student temp = student[i];
        student[i] = student[j];
        student[j] = temp;
      }
    }
  }
  DisplayAllStudents(student, count);
}
 
void SortStudents(Student* student, int count) {
  int selection = ShowSortingMenu();
  if (!selection)
    return;
  LessFunction less_functions[] = {NULL, &LessBySum, &LessByAvg};
  SortAndDisplayAllStudents(less_functions[selection], student, count);
}
 
int ShowMainMenu() {
  int selection = 0;
  do {
    if (selection) {
      printf("Ilegal input!\n");
      Pause();
    }
    ClearScreen();
    printf("--- Welcome To Students Scores ---\n");
    printf("1 - Input students' informations.\n");
    printf("2 - Display students' informations.\n");
    printf("3 - Sort students' informations.\n");
    printf("0 - Exit.\n");
    printf("Type your selection: ");
    scanf("%d", &selection);
  } while (selection < 0 || selection > 3);
  return selection;
}
 
int main() {
  Student students[MAX_STUDENTS];
  int count = 0;
 
  int selection = 0;
  while ((selection = ShowMainMenu())) {
    switch (selection) {
      case 1:
        count = InputAllStudent(students, MAX_STUDENTS);
        break;
      case 2:
        DisplayAllStudents(students, count);
        break;
      case 3:
        SortStudents(students, count);
        break;
      default:
        break;
    };
  }
 
  printf("Thank you, bye!\n");
  return 0;
}