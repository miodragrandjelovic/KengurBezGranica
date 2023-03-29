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

        public Assignment AddNewAssignment(AssignmentDTO assignmentDTO)
        {

            var assignment = new Assignment();

            assignment.TaskText = assignmentDTO.TaskText;
            assignment.AnswersText = assignmentDTO.AnswersText;
            assignment.Level = assignmentDTO.Level;
            assignment.Class = assignmentDTO.Class;

            var path = Path.Combine(Constants.Constants.ROOT_FOLDER, assignmentDTO.Class,assignmentDTO.Level.ToString());

            if(assignmentDTO.TaskPicture != null)
            {
                fileService.AddFile(path,assignmentDTO.TaskPicture);

                assignment.TaskPicture = assignmentDTO.TaskPicture.FileName;
            }

            if ((assignmentDTO.AnswersPictures != null) && (assignmentDTO.AnswersPictures.Count > 0))
            {
                foreach (var image in assignmentDTO.AnswersPictures)
                {
                    fileService.AddFile(path,image);

                    assignment.AnswersPictures.Add(image.FileName);
                }
            }



            return assignmentDAL.AddNewAssignment(assignment);
        }
    }
}
