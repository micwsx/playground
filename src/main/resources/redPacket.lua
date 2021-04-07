redis.replicate_commands()
local redPacket = KEYS[1]
local robber = KEYS[2]
local user = KEYS[3]

if redis.call("scard",redPacket) > 0 then
   if redis.call("sismember",robber,user)==1 then
   		return nil
   else
   		local packet = redis.call("srandmember",redPacket)
   		local got = redis.call("srem",redPacket,packet)
   		if got == 1 then
   			redis.call("sadd",robber,user)
   			redis.call("expire",robber,6)
   			return packet
   		else
   			return nil
   		end
   end
else
   return 0
end