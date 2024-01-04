using BackendKengur.DAL.Interfaces;
using MongoDB.Driver;
using BackendKengur.Models;
using BackendKengur.Models.Interfaces;
using BackendKengur.DTOComponents;
using System.Collections.Generic;
using System;
using MongoDB.Bson.Serialization;
using MongoDB.Bson;

namespace BackendKengur.DAL
{
    public class AssignmentDAL : IAssignmentDAL
    {

        private IMongoCollection<Assignment> _assignments;

        public AssignmentDAL(IKengurDatabaseSettings settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _assignments = database.GetCollection<Assignment>(settings.AssignmentCollectionName);
        }

        public async Task<Assignment> AddNewAssignment(Assignment assignment)
        {
            await _assignments.InsertOneAsync(assignment);
            return assignment;
        }

        public async Task<List<Assignment>> GetAssignmentsByClass(string Class)
        {
            var tasks3 = await GetRandomTasks(_assignments, Class, 3, 2);
            var tasks4 = await GetRandomTasks(_assignments, Class, 4, 2);
            var tasks5 = await GetRandomTasks(_assignments, Class, 5, 2);
            
            return tasks3.Union(tasks4).Union(tasks5).ToList();
        }

        static async Task<List<Assignment>> GetRandomTasks(IMongoCollection<Assignment> collection, string className, int level, int count)
        {
            var pipeline = new List<BsonDocument>
        {
            BsonDocument.Parse("{ $match: { class: '" + className + "', level: " + level + " } }"), // Filter by Class and Level
            BsonDocument.Parse("{ $sample: { size: " + count + " } }") // Use $sample to get random documents
        };

            var cursor = await collection.AggregateAsync<Assignment>(pipeline);
            var randomTasks = await cursor.ToListAsync();

            return randomTasks;
        }

        public async Task<List<Assignment>> GetTasksFiltered(string Class, int Level)
        {
            var result = await _assignments.FindAsync(assignment => Class.Equals(assignment.Class) && Level == assignment.Level);
            return await result.ToListAsync();
        }

        public async Task<bool> SendStatistic(List<TaskEfficiencyDTO> list)
        {
            foreach (var task in list) 
            {
                UpdateDefinition<Assignment> update;

                if (task.isCorrect)
                {
                    update = Builders<Assignment>.Update
                    .Inc("correct", 1);
                }
                else
                {
                    update = Builders<Assignment>.Update
                    .Inc("wrong", 1);
                }
                

                var updateAssigment = await _assignments.UpdateOneAsync(a => a.Id == task.id, update);

            }
            return true;
        }
    }
}
