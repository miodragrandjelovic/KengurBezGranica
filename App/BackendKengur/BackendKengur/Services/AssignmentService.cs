using BackendKengur.DAL.Interfaces;
using BackendKengur.DTOComponents;
using BackendKengur.Models;
using BackendKengur.Services.Interfaces;

namespace BackendKengur.Services
{
    public class AssignmentService : IAssignmentService
    {
        private readonly IAssignmentDAL assignmentDAL;
        private readonly IFileService fileService;

        public AssignmentService(IAssignmentDAL assignmentDAL, IFileService fileService)
        {
            this.assignmentDAL = assignmentDAL;
            this.fileService = fileService;
        }

        public async Task<Assignment> AddNewAssignment(AssignmentDTO assignmentDTO)
        {

            var assignment = new Assignment();

            assignment.TaskText = assignmentDTO.TaskText;
            assignment.AnswersText = assignmentDTO.AnswersText;
            assignment.Level = assignmentDTO.Level;
            assignment.Class = assignmentDTO.Class;
            assignment.CorrectAnswerIndex = assignmentDTO.CorrectAnswerIndex;

            var path = Path.Combine(Constants.Constants.ROOT_FOLDER, assignmentDTO.Class,assignmentDTO.Level.ToString());

            if(assignmentDTO.TaskPicture != null)
            {
                await fileService.AddFile(path,assignmentDTO.TaskPicture);

                assignment.TaskPicture = assignmentDTO.TaskPicture.FileName;
            }

            if ((assignmentDTO.AnswersPictures != null) && (assignmentDTO.AnswersPictures.Count > 0))
            {
                foreach (var image in assignmentDTO.AnswersPictures)
                {
                    await fileService.AddFile(path,image);

                    assignment.AnswersPictures.Add(image.FileName);
                }
            }



            return await assignmentDAL.AddNewAssignment(assignment);
        }

        public async Task<List<Assignment>> GetAssignmentsByClass(string Class)
        {
            var assignments = await assignmentDAL.GetAssignmentsByClass(Class);

            // dodavanje full path-a slikama da bi moglo da im se pristupi sa fronta
            foreach (var assignment in assignments)
            {
                if(assignment.TaskPicture!="")
                    assignment.TaskPicture = Path.Combine(Constants.Constants.ROOT_FOLDER,assignment.Class,assignment.Level.ToString(),assignment.TaskPicture);

                for(int i = 0; i < assignment.AnswersPictures.Count; i++)
                {
                    assignment.AnswersPictures[i]= Path.Combine(Constants.Constants.ROOT_FOLDER, assignment.Class, assignment.Level.ToString(), assignment.AnswersPictures[i]);
                }

            }
            return assignments;

        }

        public async Task<List<Assignment>> GetTasksFiltered(string Class, int Level)
        {
            var assignments = await assignmentDAL.GetTasksFiltered(Class, Level);

            foreach (var assignment in assignments)
            {
                if (assignment.TaskPicture != "")
                    assignment.TaskPicture = Path.Combine(Constants.Constants.ROOT_FOLDER, assignment.Class, assignment.Level.ToString(), assignment.TaskPicture);

                for (int i = 0; i < assignment.AnswersPictures.Count; i++)
                {
                    assignment.AnswersPictures[i] = Path.Combine(Constants.Constants.ROOT_FOLDER, assignment.Class, assignment.Level.ToString(), assignment.AnswersPictures[i]);
                }

            }
            return assignments;

        }

        public async Task<bool> SendStatistic(List<TaskEfficiencyDTO> list)
        {
            return await assignmentDAL.SendStatistic(list);
        }
    }
}
