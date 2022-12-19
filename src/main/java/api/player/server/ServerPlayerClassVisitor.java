package api.player.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public final class ServerPlayerClassVisitor extends ClassVisitor {
    public static final String targetClassName = "net.minecraft.entity.player.EntityPlayerMP";
    private boolean hadLocalAddExhaustion;
    private boolean hadLocalAddExperience;
    private boolean hadLocalAddExperienceLevel;
    private boolean hadLocalAddMovementStat;
    private boolean hadLocalAttackEntityFrom;
    private boolean hadLocalAttackTargetEntityWithCurrentItem;
    private boolean hadLocalCanBreatheUnderwater;
    private boolean hadLocalCanHarvestBlock;
    private boolean hadLocalCanPlayerEdit;
    private boolean hadLocalCanTriggerWalking;
    private boolean hadLocalClonePlayer;
    private boolean hadLocalDamageEntity;
    private boolean hadLocalDisplayGUIChest;
    private boolean hadLocalDisplayGUIDispenser;
    private boolean hadLocalDisplayGUIFurnace;
    private boolean hadLocalDisplayGUIWorkbench;
    private boolean hadLocalDropOneItem;
    private boolean hadLocalDropPlayerItem;
    private boolean hadLocalFall;
    private boolean hadLocalGetAIMoveSpeed;
    private boolean hadLocalGetCurrentPlayerStrVsBlock;
    private boolean hadLocalGetCurrentPlayerStrVsBlockForge;
    private boolean hadLocalGetDistanceSq;
    private boolean hadLocalGetBrightness;
    private boolean hadLocalGetEyeHeight;
    private boolean hadLocalHeal;
    private boolean hadLocalIsEntityInsideOpaqueBlock;
    private boolean hadLocalIsInWater;
    private boolean hadLocalIsInsideOfMaterial;
    private boolean hadLocalIsOnLadder;
    private boolean hadLocalIsPlayerSleeping;
    private boolean hadLocalIsSneaking;
    private boolean hadLocalJump;
    private boolean hadLocalKnockBack;
    private boolean hadLocalMountEntity;
    private boolean hadLocalMoveEntity;
    private boolean hadLocalMoveEntityWithHeading;
    private boolean hadLocalMoveFlying;
    private boolean hadLocalOnDeath;
    private boolean hadLocalOnLivingUpdate;
    private boolean hadLocalOnKillEntity;
    private boolean hadLocalOnStruckByLightning;
    private boolean hadLocalOnUpdate;
    private boolean hadLocalOnUpdateEntity;
    private boolean hadLocalReadEntityFromNBT;
    private boolean hadLocalSetDead;
    private boolean hadLocalSetEntityActionState;
    private boolean hadLocalSetPosition;
    private boolean hadLocalSetSneaking;
    private boolean hadLocalSetSprinting;
    private boolean hadLocalSwingItem;
    private boolean hadLocalUpdateEntityActionState;
    private boolean hadLocalUpdatePotionEffects;
    private boolean hadLocalUpdateRidden;
    private boolean hadLocalWakeUpPlayer;
    private boolean hadLocalWriteEntityToNBT;
    private final boolean isObfuscated;

    public static byte[] transform(byte[] bytes, boolean isObfuscated) {
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(bytes);
            ClassReader cr = new ClassReader(in);
            ClassWriter cw = new ClassWriter(1);
            ServerPlayerClassVisitor p = new ServerPlayerClassVisitor(cw, isObfuscated);
            cr.accept(p, 0);
            byte[] result = cw.toByteArray();
            in.close();
            return result;
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    public ServerPlayerClassVisitor(ClassVisitor classVisitor, boolean isObfuscated) {
        super(262144, classVisitor);
        this.isObfuscated = isObfuscated;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        String[] newInterfaces = new String[interfaces.length + 1];

        System.arraycopy(interfaces, 0, newInterfaces, 0, interfaces.length);

        newInterfaces[interfaces.length] = "api/player/server/IServerPlayerAPI";
        super.visit(version, access, name, signature, superName, newInterfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        if (name.equals("<init>")) {
            return new ServerPlayerConstructorVisitor(
                    super.visitMethod(access, name, desc, signature, exceptions), this.isObfuscated);
        } else if (name.equals(this.isObfuscated ? "a" : "addExhaustion") && desc.equals("(F)V")) {
            this.hadLocalAddExhaustion = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localAddExhaustion", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "v" : "addExperience") && desc.equals("(I)V")) {
            this.hadLocalAddExperience = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localAddExperience", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "addExperienceLevel") && desc.equals("(I)V")) {
            this.hadLocalAddExperienceLevel = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localAddExperienceLevel", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "k" : "addMovementStat") && desc.equals("(DDD)V")) {
            this.hadLocalAddMovementStat = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localAddMovementStat", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "attackEntityFrom")
                && desc.equals(this.isObfuscated ? "(Lro;F)Z" : "(Lnet/minecraft/util/DamageSource;F)Z")) {
            this.hadLocalAttackEntityFrom = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localAttackEntityFrom", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "r" : "attackEntityPlayerMPEntityWithCurrentItem")
                && desc.equals(this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V")) {
            this.hadLocalAttackTargetEntityWithCurrentItem = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localAttackTargetEntityWithCurrentItem",
                    desc,
                    signature,
                    exceptions);
        } else if (name.equals(this.isObfuscated ? "aE" : "canBreatheUnderwater") && desc.equals("()Z")) {
            this.hadLocalCanBreatheUnderwater = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localCanBreatheUnderwater", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "canHarvestBlock")
                && desc.equals(this.isObfuscated ? "(Laji;)Z" : "(Lnet/minecraft/block/Block;)Z")) {
            this.hadLocalCanHarvestBlock = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localCanHarvestBlock", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "canPlayerEdit")
                && desc.equals(this.isObfuscated ? "(IIIILadd;)Z" : "(IIIILnet/minecraft/item/ItemStack;)Z")) {
            this.hadLocalCanPlayerEdit = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localCanPlayerEdit", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "g_" : "canTriggerWalking") && desc.equals("()Z")) {
            this.hadLocalCanTriggerWalking = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localCanTriggerWalking", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "clonePlayer")
                && desc.equals(this.isObfuscated ? "(Lyz;Z)V" : "(Lnet/minecraft/entity/player/EntityPlayer;Z)V")) {
            this.hadLocalClonePlayer = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localClonePlayer", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "d" : "damageEntity")
                && desc.equals(this.isObfuscated ? "(Lro;F)V" : "(Lnet/minecraft/util/DamageSource;F)V")) {
            this.hadLocalDamageEntity = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDamageEntity", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "displayGUIChest")
                && desc.equals(this.isObfuscated ? "(Lrb;)V" : "(Lnet/minecraft/inventory/IInventory;)V")) {
            this.hadLocalDisplayGUIChest = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDisplayGUIChest", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "func_146102_a")
                && desc.equals(this.isObfuscated ? "(Lapb;)V" : "(Lnet/minecraft/tileentity/TileEntityDispenser;)V")) {
            this.hadLocalDisplayGUIDispenser = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDisplayGUIDispenser", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "func_146101_a")
                && desc.equals(this.isObfuscated ? "(Lapg;)V" : "(Lnet/minecraft/tileentity/TileEntityFurnace;)V")) {
            this.hadLocalDisplayGUIFurnace = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDisplayGUIFurnace", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "b" : "displayGUIWorkbench") && desc.equals("(III)V")) {
            this.hadLocalDisplayGUIWorkbench = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDisplayGUIWorkbench", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "dropOneItem")
                && desc.equals(this.isObfuscated ? "(Z)Lxk;" : "(Z)Lnet/minecraft/entity/item/EntityItem;")) {
            this.hadLocalDropOneItem = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDropOneItem", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "dropPlayerItemWithRandomChoice")
                && desc.equals(
                        this.isObfuscated
                                ? "(Ladd;Z)Lxk;"
                                : "(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/item/EntityItem;")) {
            this.hadLocalDropPlayerItem = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDropPlayerItem", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "b" : "fall") && desc.equals("(F)V")) {
            this.hadLocalFall = true;
            return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localFall", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "bl" : "getAIMoveSpeed") && desc.equals("()F")) {
            this.hadLocalGetAIMoveSpeed = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetAIMoveSpeed", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "getCurrentPlayerStrVsBlock")
                && desc.equals(this.isObfuscated ? "(Laji;Z)F" : "(Lnet/minecraft/block/Block;Z)F")) {
            this.hadLocalGetCurrentPlayerStrVsBlock = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localGetCurrentPlayerStrVsBlock",
                    desc,
                    signature,
                    exceptions);
        } else if (name.equals("getBreakSpeed")
                && desc.equals(this.isObfuscated ? "(Laji;ZI)F" : "(Lnet/minecraft/block/Block;ZI)F")) {
            this.hadLocalGetCurrentPlayerStrVsBlockForge = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localGetCurrentPlayerStrVsBlockForge",
                    desc,
                    signature,
                    exceptions);
        } else if (name.equals(this.isObfuscated ? "e" : "getDistanceSq") && desc.equals("(DDD)D")) {
            this.hadLocalGetDistanceSq = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetDistanceSq", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "d" : "getBrightness") && desc.equals("(F)F")) {
            this.hadLocalGetBrightness = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetBrightness", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "g" : "getEyeHeight") && desc.equals("()F")) {
            this.hadLocalGetEyeHeight = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetEyeHeight", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "f" : "heal") && desc.equals("(F)V")) {
            this.hadLocalHeal = true;
            return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localHeal", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "aa" : "isEntityInsideOpaqueBlock") && desc.equals("()Z")) {
            this.hadLocalIsEntityInsideOpaqueBlock = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localIsEntityInsideOpaqueBlock",
                    desc,
                    signature,
                    exceptions);
        } else if (name.equals(this.isObfuscated ? "M" : "isInWater") && desc.equals("()Z")) {
            this.hadLocalIsInWater = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsInWater", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "isInsideOfMaterial")
                && desc.equals(this.isObfuscated ? "(Lawt;)Z" : "(Lnet/minecraft/block/material/Material;)Z")) {
            this.hadLocalIsInsideOfMaterial = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsInsideOfMaterial", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "h_" : "isOnLadder") && desc.equals("()Z")) {
            this.hadLocalIsOnLadder = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsOnLadder", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "bm" : "isPlayerSleeping") && desc.equals("()Z")) {
            this.hadLocalIsPlayerSleeping = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsPlayerSleeping", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "an" : "isSneaking") && desc.equals("()Z")) {
            this.hadLocalIsSneaking = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsSneaking", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "bj" : "jump") && desc.equals("()V")) {
            this.hadLocalJump = true;
            return super.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localJump", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "knockBack")
                && desc.equals(this.isObfuscated ? "(Lsa;FDD)V" : "(Lnet/minecraft/entity/Entity;FDD)V")) {
            this.hadLocalKnockBack = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localKnockBack", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "mountEntity")
                && desc.equals(this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V")) {
            this.hadLocalMountEntity = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localMountEntity", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "d" : "moveEntity") && desc.equals("(DDD)V")) {
            this.hadLocalMoveEntity = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localMoveEntity", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "e" : "moveEntityWithHeading") && desc.equals("(FF)V")) {
            this.hadLocalMoveEntityWithHeading = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localMoveEntityWithHeading", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "moveFlying") && desc.equals("(FFF)V")) {
            this.hadLocalMoveFlying = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localMoveFlying", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "onDeath")
                && desc.equals(this.isObfuscated ? "(Lro;)V" : "(Lnet/minecraft/util/DamageSource;)V")) {
            this.hadLocalOnDeath = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localOnDeath", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "e" : "onLivingUpdate") && desc.equals("()V")) {
            this.hadLocalOnLivingUpdate = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localOnLivingUpdate", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "onKillEntity")
                && desc.equals(this.isObfuscated ? "(Lsv;)V" : "(Lnet/minecraft/entity/EntityLivingBase;)V")) {
            this.hadLocalOnKillEntity = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localOnKillEntity", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "onStruckByLightning")
                && desc.equals(
                        this.isObfuscated ? "(Lxh;)V" : "(Lnet/minecraft/entity/effect/EntityLightningBolt;)V")) {
            this.hadLocalOnStruckByLightning = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localOnStruckByLightning", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "h" : "onUpdate") && desc.equals("()V")) {
            this.hadLocalOnUpdate = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localOnUpdate", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "i" : "onUpdateEntity") && desc.equals("()V")) {
            this.hadLocalOnUpdateEntity = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localOnUpdateEntity", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "readEntityFromNBT")
                && desc.equals(this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V")) {
            this.hadLocalReadEntityFromNBT = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localReadEntityFromNBT", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "B" : "setDead") && desc.equals("()V")) {
            this.hadLocalSetDead = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetDead", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "setEntityActionState") && desc.equals("(FFZZ)V")) {
            this.hadLocalSetEntityActionState = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetEntityActionState", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "b" : "setPosition") && desc.equals("(DDD)V")) {
            this.hadLocalSetPosition = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetPosition", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "b" : "setSneaking") && desc.equals("(Z)V")) {
            this.hadLocalSetSneaking = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetSneaking", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "c" : "setSprinting") && desc.equals("(Z)V")) {
            this.hadLocalSetSprinting = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetSprinting", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "ba" : "swingItem") && desc.equals("()V")) {
            this.hadLocalSwingItem = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSwingItem", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "bq" : "updateEntityActionState") && desc.equals("()V")) {
            this.hadLocalUpdateEntityActionState = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localUpdateEntityActionState",
                    desc,
                    signature,
                    exceptions);
        } else if (name.equals(this.isObfuscated ? "aO" : "updatePotionEffects") && desc.equals("()V")) {
            this.hadLocalUpdatePotionEffects = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUpdatePotionEffects", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "ab" : "updateRidden") && desc.equals("()V")) {
            this.hadLocalUpdateRidden = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUpdateRidden", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "a" : "wakeUpPlayer") && desc.equals("(ZZZ)V")) {
            this.hadLocalWakeUpPlayer = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localWakeUpPlayer", desc, signature, exceptions);
        } else if (name.equals(this.isObfuscated ? "b" : "writeEntityToNBT")
                && desc.equals(this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V")) {
            this.hadLocalWriteEntityToNBT = true;
            return super.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localWriteEntityToNBT", desc, signature, exceptions);
        } else {
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void visitEnd() {
        MethodVisitor mv =
                this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "a" : "addExhaustion", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "addExhaustion",
                "(Lapi/player/server/IServerPlayerAPI;F)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realAddExhaustion", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "addExhaustion",
                "(F)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superAddExhaustion", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "addExhaustion",
                "(F)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalAddExhaustion) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localAddExhaustion", "(F)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.FLOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "addExhaustion",
                    "(F)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "v" : "addExperience", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "addExperience",
                "(Lapi/player/server/IServerPlayerAPI;I)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realAddExperience", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "v" : "addExperience",
                "(I)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superAddExperience", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "v" : "addExperience",
                "(I)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalAddExperience) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localAddExperience", "(I)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "v" : "addExperience",
                    "(I)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC, this.isObfuscated ? "a" : "addExperienceLevel", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "addExperienceLevel",
                "(Lapi/player/server/IServerPlayerAPI;I)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realAddExperienceLevel", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "addExperienceLevel",
                "(I)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superAddExperienceLevel", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "addExperienceLevel",
                "(I)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalAddExperienceLevel) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localAddExperienceLevel", "(I)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "addExperienceLevel",
                    "(I)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "k" : "addMovementStat", "(DDD)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "addMovementStat",
                "(Lapi/player/server/IServerPlayerAPI;DDD)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realAddMovementStat", "(DDD)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "k" : "addMovementStat",
                "(DDD)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superAddMovementStat", "(DDD)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "k" : "addMovementStat",
                "(DDD)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalAddMovementStat) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localAddMovementStat", "(DDD)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.DLOAD, 1);
            mv.visitVarInsn(Opcodes.DLOAD, 3);
            mv.visitVarInsn(Opcodes.DLOAD, 5);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "k" : "addMovementStat",
                    "(DDD)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "attackEntityFrom",
                "" + (this.isObfuscated ? "(Lro;F)Z" : "(Lnet/minecraft/util/DamageSource;F)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "attackEntityFrom",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Lro;F" : "Lnet/minecraft/util/DamageSource;F") + ")Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realAttackEntityFrom",
                "" + (this.isObfuscated ? "(Lro;F)Z" : "(Lnet/minecraft/util/DamageSource;F)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "attackEntityFrom",
                "" + (this.isObfuscated ? "(Lro;F)Z" : "(Lnet/minecraft/util/DamageSource;F)Z") + "");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superAttackEntityFrom",
                "" + (this.isObfuscated ? "(Lro;F)Z" : "(Lnet/minecraft/util/DamageSource;F)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "attackEntityFrom",
                "" + (this.isObfuscated ? "(Lro;F)Z" : "(Lnet/minecraft/util/DamageSource;F)Z") + "");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalAttackEntityFrom) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localAttackEntityFrom",
                    "" + (this.isObfuscated ? "(Lro;F)Z" : "(Lnet/minecraft/util/DamageSource;F)Z") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitVarInsn(Opcodes.FLOAD, 2);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "attackEntityFrom",
                    "" + (this.isObfuscated ? "(Lro;F)Z" : "(Lnet/minecraft/util/DamageSource;F)Z") + "");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "r" : "attackEntityPlayerMPEntityWithCurrentItem",
                "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "attackTargetEntityWithCurrentItem",
                "(Lapi/player/server/IServerPlayerAPI;" + (this.isObfuscated ? "Lsa;" : "Lnet/minecraft/entity/Entity;")
                        + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realAttackTargetEntityWithCurrentItem",
                "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "r" : "attackEntityPlayerMPEntityWithCurrentItem",
                "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superAttackTargetEntityWithCurrentItem",
                "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "r" : "attackEntityPlayerMPEntityWithCurrentItem",
                "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalAttackTargetEntityWithCurrentItem) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localAttackTargetEntityWithCurrentItem",
                    "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "r" : "attackEntityPlayerMPEntityWithCurrentItem",
                    "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC, this.isObfuscated ? "aE" : "canBreatheUnderwater", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "canBreatheUnderwater",
                "(Lapi/player/server/IServerPlayerAPI;)Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realCanBreatheUnderwater", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aE" : "canBreatheUnderwater",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superCanBreatheUnderwater", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "aE" : "canBreatheUnderwater",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalCanBreatheUnderwater) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localCanBreatheUnderwater", "()Z", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "aE" : "canBreatheUnderwater",
                    "()Z");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "canHarvestBlock",
                "" + (this.isObfuscated ? "(Laji;)Z" : "(Lnet/minecraft/block/Block;)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "canHarvestBlock",
                "(Lapi/player/server/IServerPlayerAPI;" + (this.isObfuscated ? "Laji;" : "Lnet/minecraft/block/Block;")
                        + ")Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realCanHarvestBlock",
                "" + (this.isObfuscated ? "(Laji;)Z" : "(Lnet/minecraft/block/Block;)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "canHarvestBlock",
                "" + (this.isObfuscated ? "(Laji;)Z" : "(Lnet/minecraft/block/Block;)Z") + "");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superCanHarvestBlock",
                "" + (this.isObfuscated ? "(Laji;)Z" : "(Lnet/minecraft/block/Block;)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "canHarvestBlock",
                "" + (this.isObfuscated ? "(Laji;)Z" : "(Lnet/minecraft/block/Block;)Z") + "");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalCanHarvestBlock) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localCanHarvestBlock",
                    "" + (this.isObfuscated ? "(Laji;)Z" : "(Lnet/minecraft/block/Block;)Z") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "canHarvestBlock",
                    "" + (this.isObfuscated ? "(Laji;)Z" : "(Lnet/minecraft/block/Block;)Z") + "");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "canPlayerEdit",
                "" + (this.isObfuscated ? "(IIIILadd;)Z" : "(IIIILnet/minecraft/item/ItemStack;)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitVarInsn(Opcodes.ILOAD, 4);
        mv.visitVarInsn(Opcodes.ALOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "canPlayerEdit",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "IIIILadd;" : "IIIILnet/minecraft/item/ItemStack;") + ")Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realCanPlayerEdit",
                "" + (this.isObfuscated ? "(IIIILadd;)Z" : "(IIIILnet/minecraft/item/ItemStack;)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitVarInsn(Opcodes.ILOAD, 4);
        mv.visitVarInsn(Opcodes.ALOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "canPlayerEdit",
                "" + (this.isObfuscated ? "(IIIILadd;)Z" : "(IIIILnet/minecraft/item/ItemStack;)Z") + "");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superCanPlayerEdit",
                "" + (this.isObfuscated ? "(IIIILadd;)Z" : "(IIIILnet/minecraft/item/ItemStack;)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitVarInsn(Opcodes.ILOAD, 4);
        mv.visitVarInsn(Opcodes.ALOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "canPlayerEdit",
                "" + (this.isObfuscated ? "(IIIILadd;)Z" : "(IIIILnet/minecraft/item/ItemStack;)Z") + "");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalCanPlayerEdit) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localCanPlayerEdit",
                    "" + (this.isObfuscated ? "(IIIILadd;)Z" : "(IIIILnet/minecraft/item/ItemStack;)Z") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitVarInsn(Opcodes.ILOAD, 4);
            mv.visitVarInsn(Opcodes.ALOAD, 5);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "canPlayerEdit",
                    "" + (this.isObfuscated ? "(IIIILadd;)Z" : "(IIIILnet/minecraft/item/ItemStack;)Z") + "");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "g_" : "canTriggerWalking", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "canTriggerWalking",
                "(Lapi/player/server/IServerPlayerAPI;)Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realCanTriggerWalking", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "g_" : "canTriggerWalking",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superCanTriggerWalking", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "g_" : "canTriggerWalking",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalCanTriggerWalking) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localCanTriggerWalking", "()Z", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "g_" : "canTriggerWalking",
                    "()Z");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "clonePlayer",
                "" + (this.isObfuscated ? "(Lyz;Z)V" : "(Lnet/minecraft/entity/player/EntityPlayer;Z)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "clonePlayer",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Lyz;Z" : "Lnet/minecraft/entity/player/EntityPlayer;Z") + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realClonePlayer",
                "" + (this.isObfuscated ? "(Lyz;Z)V" : "(Lnet/minecraft/entity/player/EntityPlayer;Z)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "clonePlayer",
                "" + (this.isObfuscated ? "(Lyz;Z)V" : "(Lnet/minecraft/entity/player/EntityPlayer;Z)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superClonePlayer",
                "" + (this.isObfuscated ? "(Lyz;Z)V" : "(Lnet/minecraft/entity/player/EntityPlayer;Z)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "clonePlayer",
                "" + (this.isObfuscated ? "(Lyz;Z)V" : "(Lnet/minecraft/entity/player/EntityPlayer;Z)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalClonePlayer) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localClonePlayer",
                    "" + (this.isObfuscated ? "(Lyz;Z)V" : "(Lnet/minecraft/entity/player/EntityPlayer;Z)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "clonePlayer",
                    "" + (this.isObfuscated ? "(Lyz;Z)V" : "(Lnet/minecraft/entity/player/EntityPlayer;Z)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "d" : "damageEntity",
                "" + (this.isObfuscated ? "(Lro;F)V" : "(Lnet/minecraft/util/DamageSource;F)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "damageEntity",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Lro;F" : "Lnet/minecraft/util/DamageSource;F") + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realDamageEntity",
                "" + (this.isObfuscated ? "(Lro;F)V" : "(Lnet/minecraft/util/DamageSource;F)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "d" : "damageEntity",
                "" + (this.isObfuscated ? "(Lro;F)V" : "(Lnet/minecraft/util/DamageSource;F)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superDamageEntity",
                "" + (this.isObfuscated ? "(Lro;F)V" : "(Lnet/minecraft/util/DamageSource;F)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "d" : "damageEntity",
                "" + (this.isObfuscated ? "(Lro;F)V" : "(Lnet/minecraft/util/DamageSource;F)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalDamageEntity) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localDamageEntity",
                    "" + (this.isObfuscated ? "(Lro;F)V" : "(Lnet/minecraft/util/DamageSource;F)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitVarInsn(Opcodes.FLOAD, 2);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "d" : "damageEntity",
                    "" + (this.isObfuscated ? "(Lro;F)V" : "(Lnet/minecraft/util/DamageSource;F)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "displayGUIChest",
                "" + (this.isObfuscated ? "(Lrb;)V" : "(Lnet/minecraft/inventory/IInventory;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "displayGUIChest",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Lrb;" : "Lnet/minecraft/inventory/IInventory;") + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realDisplayGUIChest",
                "" + (this.isObfuscated ? "(Lrb;)V" : "(Lnet/minecraft/inventory/IInventory;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "displayGUIChest",
                "" + (this.isObfuscated ? "(Lrb;)V" : "(Lnet/minecraft/inventory/IInventory;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superDisplayGUIChest",
                "" + (this.isObfuscated ? "(Lrb;)V" : "(Lnet/minecraft/inventory/IInventory;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "displayGUIChest",
                "" + (this.isObfuscated ? "(Lrb;)V" : "(Lnet/minecraft/inventory/IInventory;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalDisplayGUIChest) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localDisplayGUIChest",
                    "" + (this.isObfuscated ? "(Lrb;)V" : "(Lnet/minecraft/inventory/IInventory;)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "displayGUIChest",
                    "" + (this.isObfuscated ? "(Lrb;)V" : "(Lnet/minecraft/inventory/IInventory;)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "func_146102_a",
                "" + (this.isObfuscated ? "(Lapb;)V" : "(Lnet/minecraft/tileentity/TileEntityDispenser;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "displayGUIDispenser",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Lapb;" : "Lnet/minecraft/tileentity/TileEntityDispenser;") + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realDisplayGUIDispenser",
                "" + (this.isObfuscated ? "(Lapb;)V" : "(Lnet/minecraft/tileentity/TileEntityDispenser;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "func_146102_a",
                "" + (this.isObfuscated ? "(Lapb;)V" : "(Lnet/minecraft/tileentity/TileEntityDispenser;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superDisplayGUIDispenser",
                "" + (this.isObfuscated ? "(Lapb;)V" : "(Lnet/minecraft/tileentity/TileEntityDispenser;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "func_146102_a",
                "" + (this.isObfuscated ? "(Lapb;)V" : "(Lnet/minecraft/tileentity/TileEntityDispenser;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalDisplayGUIDispenser) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localDisplayGUIDispenser",
                    "" + (this.isObfuscated ? "(Lapb;)V" : "(Lnet/minecraft/tileentity/TileEntityDispenser;)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "func_146102_a",
                    "" + (this.isObfuscated ? "(Lapb;)V" : "(Lnet/minecraft/tileentity/TileEntityDispenser;)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "func_146101_a",
                "" + (this.isObfuscated ? "(Lapg;)V" : "(Lnet/minecraft/tileentity/TileEntityFurnace;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "displayGUIFurnace",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Lapg;" : "Lnet/minecraft/tileentity/TileEntityFurnace;") + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realDisplayGUIFurnace",
                "" + (this.isObfuscated ? "(Lapg;)V" : "(Lnet/minecraft/tileentity/TileEntityFurnace;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "func_146101_a",
                "" + (this.isObfuscated ? "(Lapg;)V" : "(Lnet/minecraft/tileentity/TileEntityFurnace;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superDisplayGUIFurnace",
                "" + (this.isObfuscated ? "(Lapg;)V" : "(Lnet/minecraft/tileentity/TileEntityFurnace;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "func_146101_a",
                "" + (this.isObfuscated ? "(Lapg;)V" : "(Lnet/minecraft/tileentity/TileEntityFurnace;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalDisplayGUIFurnace) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localDisplayGUIFurnace",
                    "" + (this.isObfuscated ? "(Lapg;)V" : "(Lnet/minecraft/tileentity/TileEntityFurnace;)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "func_146101_a",
                    "" + (this.isObfuscated ? "(Lapg;)V" : "(Lnet/minecraft/tileentity/TileEntityFurnace;)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC, this.isObfuscated ? "b" : "displayGUIWorkbench", "(III)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "displayGUIWorkbench",
                "(Lapi/player/server/IServerPlayerAPI;III)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realDisplayGUIWorkbench", "(III)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "b" : "displayGUIWorkbench",
                "(III)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superDisplayGUIWorkbench", "(III)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "b" : "displayGUIWorkbench",
                "(III)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalDisplayGUIWorkbench) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localDisplayGUIWorkbench", "(III)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "b" : "displayGUIWorkbench",
                    "(III)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "dropOneItem",
                "" + (this.isObfuscated ? "(Z)Lxk;" : "(Z)Lnet/minecraft/entity/item/EntityItem;") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "dropOneItem",
                "(Lapi/player/server/IServerPlayerAPI;Z)"
                        + (this.isObfuscated ? "Lxk;" : "Lnet/minecraft/entity/item/EntityItem;") + "");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realDropOneItem",
                "" + (this.isObfuscated ? "(Z)Lxk;" : "(Z)Lnet/minecraft/entity/item/EntityItem;") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "dropOneItem",
                "" + (this.isObfuscated ? "(Z)Lxk;" : "(Z)Lnet/minecraft/entity/item/EntityItem;") + "");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superDropOneItem",
                "" + (this.isObfuscated ? "(Z)Lxk;" : "(Z)Lnet/minecraft/entity/item/EntityItem;") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "dropOneItem",
                "" + (this.isObfuscated ? "(Z)Lxk;" : "(Z)Lnet/minecraft/entity/item/EntityItem;") + "");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalDropOneItem) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localDropOneItem",
                    "" + (this.isObfuscated ? "(Z)Lxk;" : "(Z)Lnet/minecraft/entity/item/EntityItem;") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "dropOneItem",
                    "" + (this.isObfuscated ? "(Z)Lxk;" : "(Z)Lnet/minecraft/entity/item/EntityItem;") + "");
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "dropPlayerItemWithRandomChoice",
                ""
                        + (this.isObfuscated
                                ? "(Ladd;Z)Lxk;"
                                : "(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/item/EntityItem;")
                        + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "dropPlayerItem",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Ladd;Z" : "Lnet/minecraft/item/ItemStack;Z") + ")"
                        + (this.isObfuscated ? "Lxk;" : "Lnet/minecraft/entity/item/EntityItem;") + "");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realDropPlayerItem",
                ""
                        + (this.isObfuscated
                                ? "(Ladd;Z)Lxk;"
                                : "(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/item/EntityItem;")
                        + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "dropPlayerItemWithRandomChoice",
                ""
                        + (this.isObfuscated
                                ? "(Ladd;Z)Lxk;"
                                : "(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/item/EntityItem;")
                        + "");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superDropPlayerItem",
                ""
                        + (this.isObfuscated
                                ? "(Ladd;Z)Lxk;"
                                : "(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/item/EntityItem;")
                        + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "dropPlayerItemWithRandomChoice",
                ""
                        + (this.isObfuscated
                                ? "(Ladd;Z)Lxk;"
                                : "(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/item/EntityItem;")
                        + "");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalDropPlayerItem) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localDropPlayerItem",
                    ""
                            + (this.isObfuscated
                                    ? "(Ladd;Z)Lxk;"
                                    : "(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/item/EntityItem;")
                            + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "dropPlayerItemWithRandomChoice",
                    ""
                            + (this.isObfuscated
                                    ? "(Ladd;Z)Lxk;"
                                    : "(Lnet/minecraft/item/ItemStack;Z)Lnet/minecraft/entity/item/EntityItem;")
                            + "");
            mv.visitInsn(Opcodes.ARETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "b" : "fall", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "fall",
                "(Lapi/player/server/IServerPlayerAPI;F)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realFall", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "b" : "fall",
                "(F)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superFall", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "b" : "fall",
                "(F)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalFall) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localFall", "(F)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.FLOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "b" : "fall",
                    "(F)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "bl" : "getAIMoveSpeed", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "getAIMoveSpeed",
                "(Lapi/player/server/IServerPlayerAPI;)F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetAIMoveSpeed", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bl" : "getAIMoveSpeed",
                "()F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetAIMoveSpeed", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "bl" : "getAIMoveSpeed",
                "()F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalGetAIMoveSpeed) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetAIMoveSpeed", "()F", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "bl" : "getAIMoveSpeed",
                    "()F");
            mv.visitInsn(Opcodes.FRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "getCurrentPlayerStrVsBlock",
                "" + (this.isObfuscated ? "(Laji;Z)F" : "(Lnet/minecraft/block/Block;Z)F") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "getCurrentPlayerStrVsBlock",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Laji;Z" : "Lnet/minecraft/block/Block;Z") + ")F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realGetCurrentPlayerStrVsBlock",
                "" + (this.isObfuscated ? "(Laji;Z)F" : "(Lnet/minecraft/block/Block;Z)F") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "getCurrentPlayerStrVsBlock",
                "" + (this.isObfuscated ? "(Laji;Z)F" : "(Lnet/minecraft/block/Block;Z)F") + "");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superGetCurrentPlayerStrVsBlock",
                "" + (this.isObfuscated ? "(Laji;Z)F" : "(Lnet/minecraft/block/Block;Z)F") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "getCurrentPlayerStrVsBlock",
                "" + (this.isObfuscated ? "(Laji;Z)F" : "(Lnet/minecraft/block/Block;Z)F") + "");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalGetCurrentPlayerStrVsBlock) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localGetCurrentPlayerStrVsBlock",
                    "" + (this.isObfuscated ? "(Laji;Z)F" : "(Lnet/minecraft/block/Block;Z)F") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "getCurrentPlayerStrVsBlock",
                    "" + (this.isObfuscated ? "(Laji;Z)F" : "(Lnet/minecraft/block/Block;Z)F") + "");
            mv.visitInsn(Opcodes.FRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                "getBreakSpeed",
                "" + (this.isObfuscated ? "(Laji;ZI)F" : "(Lnet/minecraft/block/Block;ZI)F") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "getCurrentPlayerStrVsBlockForge",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Laji;ZI" : "Lnet/minecraft/block/Block;ZI") + ")F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realGetCurrentPlayerStrVsBlockForge",
                "" + (this.isObfuscated ? "(Laji;ZI)F" : "(Lnet/minecraft/block/Block;ZI)F") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                "getBreakSpeed",
                "" + (this.isObfuscated ? "(Laji;ZI)F" : "(Lnet/minecraft/block/Block;ZI)F") + "");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superGetCurrentPlayerStrVsBlockForge",
                "" + (this.isObfuscated ? "(Laji;ZI)F" : "(Lnet/minecraft/block/Block;ZI)F") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                "getBreakSpeed",
                "" + (this.isObfuscated ? "(Laji;ZI)F" : "(Lnet/minecraft/block/Block;ZI)F") + "");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalGetCurrentPlayerStrVsBlockForge) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localGetCurrentPlayerStrVsBlockForge",
                    "" + (this.isObfuscated ? "(Laji;ZI)F" : "(Lnet/minecraft/block/Block;ZI)F") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    "getBreakSpeed",
                    "" + (this.isObfuscated ? "(Laji;ZI)F" : "(Lnet/minecraft/block/Block;ZI)F") + "");
            mv.visitInsn(Opcodes.FRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "e" : "getDistanceSq", "(DDD)D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "getDistanceSq",
                "(Lapi/player/server/IServerPlayerAPI;DDD)D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetDistanceSq", "(DDD)D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "e" : "getDistanceSq",
                "(DDD)D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetDistanceSq", "(DDD)D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "e" : "getDistanceSq",
                "(DDD)D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalGetDistanceSq) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetDistanceSq", "(DDD)D", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.DLOAD, 1);
            mv.visitVarInsn(Opcodes.DLOAD, 3);
            mv.visitVarInsn(Opcodes.DLOAD, 5);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "e" : "getDistanceSq",
                    "(DDD)D");
            mv.visitInsn(Opcodes.DRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "d" : "getBrightness", "(F)F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "getBrightness",
                "(Lapi/player/server/IServerPlayerAPI;F)F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetBrightness", "(F)F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "d" : "getBrightness",
                "(F)F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetBrightness", "(F)F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "d" : "getBrightness",
                "(F)F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalGetBrightness) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetBrightness", "(F)F", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.FLOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "d" : "getBrightness",
                    "(F)F");
            mv.visitInsn(Opcodes.FRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "g" : "getEyeHeight", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "getEyeHeight",
                "(Lapi/player/server/IServerPlayerAPI;)F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realGetEyeHeight", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "g" : "getEyeHeight",
                "()F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superGetEyeHeight", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "g" : "getEyeHeight",
                "()F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalGetEyeHeight) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localGetEyeHeight", "()F", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "g" : "getEyeHeight",
                    "()F");
            mv.visitInsn(Opcodes.FRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "f" : "heal", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "heal",
                "(Lapi/player/server/IServerPlayerAPI;F)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realHeal", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "f" : "heal",
                "(F)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superHeal", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "f" : "heal",
                "(F)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalHeal) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localHeal", "(F)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.FLOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "f" : "heal",
                    "(F)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC, this.isObfuscated ? "aa" : "isEntityInsideOpaqueBlock", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "isEntityInsideOpaqueBlock",
                "(Lapi/player/server/IServerPlayerAPI;)Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realIsEntityInsideOpaqueBlock", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aa" : "isEntityInsideOpaqueBlock",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superIsEntityInsideOpaqueBlock", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "aa" : "isEntityInsideOpaqueBlock",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalIsEntityInsideOpaqueBlock) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsEntityInsideOpaqueBlock", "()Z", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "aa" : "isEntityInsideOpaqueBlock",
                    "()Z");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "M" : "isInWater", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "isInWater",
                "(Lapi/player/server/IServerPlayerAPI;)Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realIsInWater", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "M" : "isInWater",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superIsInWater", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "M" : "isInWater",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalIsInWater) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsInWater", "()Z", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "M" : "isInWater",
                    "()Z");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "isInsideOfMaterial",
                "" + (this.isObfuscated ? "(Lawt;)Z" : "(Lnet/minecraft/block/material/Material;)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "isInsideOfMaterial",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Lawt;" : "Lnet/minecraft/block/material/Material;") + ")Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realIsInsideOfMaterial",
                "" + (this.isObfuscated ? "(Lawt;)Z" : "(Lnet/minecraft/block/material/Material;)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "isInsideOfMaterial",
                "" + (this.isObfuscated ? "(Lawt;)Z" : "(Lnet/minecraft/block/material/Material;)Z") + "");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superIsInsideOfMaterial",
                "" + (this.isObfuscated ? "(Lawt;)Z" : "(Lnet/minecraft/block/material/Material;)Z") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "isInsideOfMaterial",
                "" + (this.isObfuscated ? "(Lawt;)Z" : "(Lnet/minecraft/block/material/Material;)Z") + "");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalIsInsideOfMaterial) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localIsInsideOfMaterial",
                    "" + (this.isObfuscated ? "(Lawt;)Z" : "(Lnet/minecraft/block/material/Material;)Z") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "isInsideOfMaterial",
                    "" + (this.isObfuscated ? "(Lawt;)Z" : "(Lnet/minecraft/block/material/Material;)Z") + "");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "h_" : "isOnLadder", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "isOnLadder",
                "(Lapi/player/server/IServerPlayerAPI;)Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realIsOnLadder", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "h_" : "isOnLadder",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superIsOnLadder", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "h_" : "isOnLadder",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalIsOnLadder) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsOnLadder", "()Z", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "h_" : "isOnLadder",
                    "()Z");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "bm" : "isPlayerSleeping", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "isPlayerSleeping",
                "(Lapi/player/server/IServerPlayerAPI;)Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realIsPlayerSleeping", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bm" : "isPlayerSleeping",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superIsPlayerSleeping", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "bm" : "isPlayerSleeping",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalIsPlayerSleeping) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsPlayerSleeping", "()Z", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "bm" : "isPlayerSleeping",
                    "()Z");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "an" : "isSneaking", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "isSneaking",
                "(Lapi/player/server/IServerPlayerAPI;)Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realIsSneaking", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "an" : "isSneaking",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superIsSneaking", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "an" : "isSneaking",
                "()Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalIsSneaking) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localIsSneaking", "()Z", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "an" : "isSneaking",
                    "()Z");
            mv.visitInsn(Opcodes.IRETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "bj" : "jump", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "jump",
                "(Lapi/player/server/IServerPlayerAPI;)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realJump", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bj" : "jump",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superJump", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "bj" : "jump",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalJump) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localJump", "()V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "bj" : "jump",
                    "()V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "knockBack",
                "" + (this.isObfuscated ? "(Lsa;FDD)V" : "(Lnet/minecraft/entity/Entity;FDD)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "knockBack",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Lsa;FDD" : "Lnet/minecraft/entity/Entity;FDD") + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realKnockBack",
                "" + (this.isObfuscated ? "(Lsa;FDD)V" : "(Lnet/minecraft/entity/Entity;FDD)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "knockBack",
                "" + (this.isObfuscated ? "(Lsa;FDD)V" : "(Lnet/minecraft/entity/Entity;FDD)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superKnockBack",
                "" + (this.isObfuscated ? "(Lsa;FDD)V" : "(Lnet/minecraft/entity/Entity;FDD)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "knockBack",
                "" + (this.isObfuscated ? "(Lsa;FDD)V" : "(Lnet/minecraft/entity/Entity;FDD)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalKnockBack) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localKnockBack",
                    "" + (this.isObfuscated ? "(Lsa;FDD)V" : "(Lnet/minecraft/entity/Entity;FDD)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitVarInsn(Opcodes.FLOAD, 2);
            mv.visitVarInsn(Opcodes.DLOAD, 3);
            mv.visitVarInsn(Opcodes.DLOAD, 5);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "knockBack",
                    "" + (this.isObfuscated ? "(Lsa;FDD)V" : "(Lnet/minecraft/entity/Entity;FDD)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "mountEntity",
                "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "mountEntity",
                "(Lapi/player/server/IServerPlayerAPI;" + (this.isObfuscated ? "Lsa;" : "Lnet/minecraft/entity/Entity;")
                        + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realMountEntity",
                "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "mountEntity",
                "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superMountEntity",
                "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "mountEntity",
                "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalMountEntity) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localMountEntity",
                    "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "mountEntity",
                    "" + (this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "d" : "moveEntity", "(DDD)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "moveEntity",
                "(Lapi/player/server/IServerPlayerAPI;DDD)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realMoveEntity", "(DDD)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "d" : "moveEntity",
                "(DDD)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superMoveEntity", "(DDD)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "d" : "moveEntity",
                "(DDD)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalMoveEntity) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localMoveEntity", "(DDD)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.DLOAD, 1);
            mv.visitVarInsn(Opcodes.DLOAD, 3);
            mv.visitVarInsn(Opcodes.DLOAD, 5);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "d" : "moveEntity",
                    "(DDD)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC, this.isObfuscated ? "e" : "moveEntityWithHeading", "(FF)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "moveEntityWithHeading",
                "(Lapi/player/server/IServerPlayerAPI;FF)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realMoveEntityWithHeading", "(FF)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "e" : "moveEntityWithHeading",
                "(FF)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superMoveEntityWithHeading", "(FF)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "e" : "moveEntityWithHeading",
                "(FF)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalMoveEntityWithHeading) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localMoveEntityWithHeading", "(FF)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.FLOAD, 1);
            mv.visitVarInsn(Opcodes.FLOAD, 2);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "e" : "moveEntityWithHeading",
                    "(FF)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "a" : "moveFlying", "(FFF)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitVarInsn(Opcodes.FLOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "moveFlying",
                "(Lapi/player/server/IServerPlayerAPI;FFF)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realMoveFlying", "(FFF)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitVarInsn(Opcodes.FLOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "moveFlying",
                "(FFF)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superMoveFlying", "(FFF)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitVarInsn(Opcodes.FLOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "moveFlying",
                "(FFF)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalMoveFlying) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localMoveFlying", "(FFF)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.FLOAD, 1);
            mv.visitVarInsn(Opcodes.FLOAD, 2);
            mv.visitVarInsn(Opcodes.FLOAD, 3);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "moveFlying",
                    "(FFF)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "onDeath",
                "" + (this.isObfuscated ? "(Lro;)V" : "(Lnet/minecraft/util/DamageSource;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "onDeath",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Lro;" : "Lnet/minecraft/util/DamageSource;") + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realOnDeath",
                "" + (this.isObfuscated ? "(Lro;)V" : "(Lnet/minecraft/util/DamageSource;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "onDeath",
                "" + (this.isObfuscated ? "(Lro;)V" : "(Lnet/minecraft/util/DamageSource;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superOnDeath",
                "" + (this.isObfuscated ? "(Lro;)V" : "(Lnet/minecraft/util/DamageSource;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "onDeath",
                "" + (this.isObfuscated ? "(Lro;)V" : "(Lnet/minecraft/util/DamageSource;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalOnDeath) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localOnDeath",
                    "" + (this.isObfuscated ? "(Lro;)V" : "(Lnet/minecraft/util/DamageSource;)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "onDeath",
                    "" + (this.isObfuscated ? "(Lro;)V" : "(Lnet/minecraft/util/DamageSource;)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "e" : "onLivingUpdate", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "onLivingUpdate",
                "(Lapi/player/server/IServerPlayerAPI;)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realOnLivingUpdate", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "e" : "onLivingUpdate",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superOnLivingUpdate", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "e" : "onLivingUpdate",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalOnLivingUpdate) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localOnLivingUpdate", "()V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "e" : "onLivingUpdate",
                    "()V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "onKillEntity",
                "" + (this.isObfuscated ? "(Lsv;)V" : "(Lnet/minecraft/entity/EntityLivingBase;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "onKillEntity",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Lsv;" : "Lnet/minecraft/entity/EntityLivingBase;") + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realOnKillEntity",
                "" + (this.isObfuscated ? "(Lsv;)V" : "(Lnet/minecraft/entity/EntityLivingBase;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "onKillEntity",
                "" + (this.isObfuscated ? "(Lsv;)V" : "(Lnet/minecraft/entity/EntityLivingBase;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superOnKillEntity",
                "" + (this.isObfuscated ? "(Lsv;)V" : "(Lnet/minecraft/entity/EntityLivingBase;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "onKillEntity",
                "" + (this.isObfuscated ? "(Lsv;)V" : "(Lnet/minecraft/entity/EntityLivingBase;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalOnKillEntity) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localOnKillEntity",
                    "" + (this.isObfuscated ? "(Lsv;)V" : "(Lnet/minecraft/entity/EntityLivingBase;)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "onKillEntity",
                    "" + (this.isObfuscated ? "(Lsv;)V" : "(Lnet/minecraft/entity/EntityLivingBase;)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "onStruckByLightning",
                "" + (this.isObfuscated ? "(Lxh;)V" : "(Lnet/minecraft/entity/effect/EntityLightningBolt;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "onStruckByLightning",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Lxh;" : "Lnet/minecraft/entity/effect/EntityLightningBolt;") + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realOnStruckByLightning",
                "" + (this.isObfuscated ? "(Lxh;)V" : "(Lnet/minecraft/entity/effect/EntityLightningBolt;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "onStruckByLightning",
                "" + (this.isObfuscated ? "(Lxh;)V" : "(Lnet/minecraft/entity/effect/EntityLightningBolt;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superOnStruckByLightning",
                "" + (this.isObfuscated ? "(Lxh;)V" : "(Lnet/minecraft/entity/effect/EntityLightningBolt;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "onStruckByLightning",
                "" + (this.isObfuscated ? "(Lxh;)V" : "(Lnet/minecraft/entity/effect/EntityLightningBolt;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalOnStruckByLightning) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localOnStruckByLightning",
                    "" + (this.isObfuscated ? "(Lxh;)V" : "(Lnet/minecraft/entity/effect/EntityLightningBolt;)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "onStruckByLightning",
                    "" + (this.isObfuscated ? "(Lxh;)V" : "(Lnet/minecraft/entity/effect/EntityLightningBolt;)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "h" : "onUpdate", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "onUpdate",
                "(Lapi/player/server/IServerPlayerAPI;)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realOnUpdate", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "h" : "onUpdate",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superOnUpdate", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "h" : "onUpdate",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalOnUpdate) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localOnUpdate", "()V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "h" : "onUpdate",
                    "()V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "i" : "onUpdateEntity", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "onUpdateEntity",
                "(Lapi/player/server/IServerPlayerAPI;)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realOnUpdateEntity", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "i" : "onUpdateEntity",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superOnUpdateEntity", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "i" : "onUpdateEntity",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalOnUpdateEntity) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localOnUpdateEntity", "()V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "i" : "onUpdateEntity",
                    "()V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "a" : "readEntityFromNBT",
                "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "readEntityFromNBT",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Ldh;" : "Lnet/minecraft/nbt/NBTTagCompound;") + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realReadEntityFromNBT",
                "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "readEntityFromNBT",
                "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superReadEntityFromNBT",
                "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "readEntityFromNBT",
                "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalReadEntityFromNBT) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localReadEntityFromNBT",
                    "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "readEntityFromNBT",
                    "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "B" : "setDead", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "setDead",
                "(Lapi/player/server/IServerPlayerAPI;)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetDead", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "B" : "setDead",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetDead", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "B" : "setDead",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalSetDead) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetDead", "()V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "B" : "setDead",
                    "()V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC, this.isObfuscated ? "a" : "setEntityActionState", "(FFZZ)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitVarInsn(Opcodes.ILOAD, 4);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "setEntityActionState",
                "(Lapi/player/server/IServerPlayerAPI;FFZZ)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetEntityActionState", "(FFZZ)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitVarInsn(Opcodes.ILOAD, 4);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "setEntityActionState",
                "(FFZZ)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetEntityActionState", "(FFZZ)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitVarInsn(Opcodes.FLOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitVarInsn(Opcodes.ILOAD, 4);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "setEntityActionState",
                "(FFZZ)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalSetEntityActionState) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetEntityActionState", "(FFZZ)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.FLOAD, 1);
            mv.visitVarInsn(Opcodes.FLOAD, 2);
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitVarInsn(Opcodes.ILOAD, 4);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "setEntityActionState",
                    "(FFZZ)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "b" : "setPosition", "(DDD)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "setPosition",
                "(Lapi/player/server/IServerPlayerAPI;DDD)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetPosition", "(DDD)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "b" : "setPosition",
                "(DDD)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetPosition", "(DDD)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitVarInsn(Opcodes.DLOAD, 3);
        mv.visitVarInsn(Opcodes.DLOAD, 5);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "b" : "setPosition",
                "(DDD)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalSetPosition) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetPosition", "(DDD)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.DLOAD, 1);
            mv.visitVarInsn(Opcodes.DLOAD, 3);
            mv.visitVarInsn(Opcodes.DLOAD, 5);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "b" : "setPosition",
                    "(DDD)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "b" : "setSneaking", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "setSneaking",
                "(Lapi/player/server/IServerPlayerAPI;Z)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetSneaking", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "b" : "setSneaking",
                "(Z)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetSneaking", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "b" : "setSneaking",
                "(Z)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalSetSneaking) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetSneaking", "(Z)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "b" : "setSneaking",
                    "(Z)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "c" : "setSprinting", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "setSprinting",
                "(Lapi/player/server/IServerPlayerAPI;Z)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSetSprinting", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "c" : "setSprinting",
                "(Z)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSetSprinting", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "c" : "setSprinting",
                "(Z)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalSetSprinting) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSetSprinting", "(Z)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "c" : "setSprinting",
                    "(Z)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "ba" : "swingItem", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "swingItem",
                "(Lapi/player/server/IServerPlayerAPI;)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realSwingItem", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ba" : "swingItem",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superSwingItem", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "ba" : "swingItem",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalSwingItem) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localSwingItem", "()V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "ba" : "swingItem",
                    "()V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC, this.isObfuscated ? "bq" : "updateEntityActionState", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "updateEntityActionState",
                "(Lapi/player/server/IServerPlayerAPI;)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realUpdateEntityActionState", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bq" : "updateEntityActionState",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superUpdateEntityActionState", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "bq" : "updateEntityActionState",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalUpdateEntityActionState) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUpdateEntityActionState", "()V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "bq" : "updateEntityActionState",
                    "()V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC, this.isObfuscated ? "aO" : "updatePotionEffects", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "updatePotionEffects",
                "(Lapi/player/server/IServerPlayerAPI;)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realUpdatePotionEffects", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aO" : "updatePotionEffects",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superUpdatePotionEffects", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "aO" : "updatePotionEffects",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalUpdatePotionEffects) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUpdatePotionEffects", "()V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "aO" : "updatePotionEffects",
                    "()V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "ab" : "updateRidden", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "updateRidden",
                "(Lapi/player/server/IServerPlayerAPI;)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realUpdateRidden", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ab" : "updateRidden",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superUpdateRidden", "()V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "ab" : "updateRidden",
                "()V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalUpdateRidden) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localUpdateRidden", "()V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "ab" : "updateRidden",
                    "()V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC, this.isObfuscated ? "a" : "wakeUpPlayer", "(ZZZ)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "wakeUpPlayer",
                "(Lapi/player/server/IServerPlayerAPI;ZZZ)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "realWakeUpPlayer", "(ZZZ)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "wakeUpPlayer",
                "(ZZZ)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "superWakeUpPlayer", "(ZZZ)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitVarInsn(Opcodes.ILOAD, 2);
        mv.visitVarInsn(Opcodes.ILOAD, 3);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "a" : "wakeUpPlayer",
                "(ZZZ)V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalWakeUpPlayer) {
            mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "localWakeUpPlayer", "(ZZZ)V", null, null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ILOAD, 1);
            mv.visitVarInsn(Opcodes.ILOAD, 2);
            mv.visitVarInsn(Opcodes.ILOAD, 3);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "a" : "wakeUpPlayer",
                    "(ZZZ)V");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC,
                this.isObfuscated ? "b" : "writeEntityToNBT",
                "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "writeEntityToNBT",
                "(Lapi/player/server/IServerPlayerAPI;"
                        + (this.isObfuscated ? "Ldh;" : "Lnet/minecraft/nbt/NBTTagCompound;") + ")V");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "realWriteEntityToNBT",
                "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "b" : "writeEntityToNBT",
                "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "superWriteEntityToNBT",
                "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                this.isObfuscated ? "b" : "writeEntityToNBT",
                "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        if (!this.hadLocalWriteEntityToNBT) {
            mv = this.cv.visitMethod(
                    Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                    "localWriteEntityToNBT",
                    "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "",
                    null,
                    null);
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitVarInsn(Opcodes.ALOAD, 1);
            mv.visitMethodInsn(
                    Opcodes.INVOKESPECIAL,
                    this.isObfuscated ? "yz" : "net/minecraft/entity/player/EntityPlayer",
                    this.isObfuscated ? "b" : "writeEntityToNBT",
                    "" + (this.isObfuscated ? "(Ldh;)V" : "(Lnet/minecraft/nbt/NBTTagCompound;)V") + "");
            mv.visitInsn(Opcodes.RETURN);
            mv.visitMaxs(0, 0);
            mv.visitEnd();
        }

        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getAddedToChunkField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ag" : "addedToChunk",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setAddedToChunkField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ag" : "addedToChunk",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getArrowHitTimerField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "av" : "arrowHitTimer",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setArrowHitTimerField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "av" : "arrowHitTimer",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getAttackTimeField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aB" : "attackTime",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setAttackTimeField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aB" : "attackTime",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getAttackedAtYawField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "az" : "attackedAtYaw",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setAttackedAtYawField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "az" : "attackedAtYaw",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getAttackingPlayerField",
                this.isObfuscated ? "()Lyz;" : "()Lnet/minecraft/entity/player/EntityPlayer;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aR" : "attackingPlayer",
                this.isObfuscated ? "Lyz;" : "Lnet/minecraft/entity/player/EntityPlayer;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setAttackingPlayerField",
                this.isObfuscated ? "(Lyz;)V" : "(Lnet/minecraft/entity/player/EntityPlayer;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aR" : "attackingPlayer",
                this.isObfuscated ? "Lyz;" : "Lnet/minecraft/entity/player/EntityPlayer;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getBoundingBoxField",
                this.isObfuscated ? "()Lazt;" : "()Lnet/minecraft/util/AxisAlignedBB;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "C" : "boundingBox",
                this.isObfuscated ? "Lazt;" : "Lnet/minecraft/util/AxisAlignedBB;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getCameraPitchField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aJ" : "cameraPitch",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setCameraPitchField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aJ" : "cameraPitch",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getCameraYawField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bs" : "cameraYaw",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setCameraYawField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bs" : "cameraYaw",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getCapabilitiesField",
                this.isObfuscated ? "()Lyw;" : "()Lnet/minecraft/entity/player/PlayerCapabilities;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bE" : "capabilities",
                this.isObfuscated ? "Lyw;" : "Lnet/minecraft/entity/player/PlayerCapabilities;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setCapabilitiesField",
                this.isObfuscated ? "(Lyw;)V" : "(Lnet/minecraft/entity/player/PlayerCapabilities;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bE" : "capabilities",
                this.isObfuscated ? "Lyw;" : "Lnet/minecraft/entity/player/PlayerCapabilities;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getChatColoursField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bW" : "chatColours",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setChatColoursField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bW" : "chatColours",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getChatVisibilityField",
                this.isObfuscated ? "()Lzb;" : "()Lnet/minecraft/entity/player/EntityPlayer$EnumChatVisibility;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bV" : "chatVisibility",
                this.isObfuscated ? "Lzb;" : "Lnet/minecraft/entity/player/EntityPlayer$EnumChatVisibility;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setChatVisibilityField",
                this.isObfuscated ? "(Lzb;)V" : "(Lnet/minecraft/entity/player/EntityPlayer$EnumChatVisibility;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bV" : "chatVisibility",
                this.isObfuscated ? "Lzb;" : "Lnet/minecraft/entity/player/EntityPlayer$EnumChatVisibility;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getChunkCoordXField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ah" : "chunkCoordX",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setChunkCoordXField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ah" : "chunkCoordX",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getChunkCoordYField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ai" : "chunkCoordY",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setChunkCoordYField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ai" : "chunkCoordY",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getChunkCoordZField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aj" : "chunkCoordZ",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setChunkCoordZField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aj" : "chunkCoordZ",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getCurrentWindowIdField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bY" : "currentWindowId",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setCurrentWindowIdField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bY" : "currentWindowId",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getDataWatcherField",
                this.isObfuscated ? "()Lte;" : "()Lnet/minecraft/entity/DataWatcher;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "af" : "dataWatcher",
                this.isObfuscated ? "Lte;" : "Lnet/minecraft/entity/DataWatcher;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setDataWatcherField",
                this.isObfuscated ? "(Lte;)V" : "(Lnet/minecraft/entity/DataWatcher;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "af" : "dataWatcher",
                this.isObfuscated ? "Lte;" : "Lnet/minecraft/entity/DataWatcher;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getDeadField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aT" : "dead",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setDeadField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aT" : "dead",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getDeathTimeField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aA" : "deathTime",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setDeathTimeField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aA" : "deathTime",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getDestroyedItemsNetCacheField",
                "()Ljava/util/List;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bN" : "destroyedItemsNetCache",
                "Ljava/util/List;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getDimensionField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ap" : "dimension",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setDimensionField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ap" : "dimension",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getDistanceWalkedModifiedField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "P" : "distanceWalkedModified",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setDistanceWalkedModifiedField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "P" : "distanceWalkedModified",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getDistanceWalkedOnStepModifiedField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "Q" : "distanceWalkedOnStepModified",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setDistanceWalkedOnStepModifiedField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "Q" : "distanceWalkedOnStepModified",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getEntityAgeField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aU" : "entityAge",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setEntityAgeField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aU" : "entityAge",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getEntityCollisionReductionField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "Y" : "entityCollisionReduction",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setEntityCollisionReductionField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "Y" : "entityCollisionReduction",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getEntityUniqueIDField", "()Ljava/util/UUID;", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ar" : "entityUniqueID",
                "Ljava/util/UUID;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setEntityUniqueIDField", "(Ljava/util/UUID;)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ar" : "entityUniqueID",
                "Ljava/util/UUID;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getExperienceField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bH" : "experience",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setExperienceField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bH" : "experience",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getExperienceLevelField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bF" : "experienceLevel",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setExperienceLevelField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bF" : "experienceLevel",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getExperienceTotalField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bG" : "experienceTotal",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setExperienceTotalField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bG" : "experienceTotal",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getFallDistanceField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "R" : "fallDistance",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setFallDistanceField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "R" : "fallDistance",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_110154_aXField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aW" : "field_110154_aX",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_110154_aXField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aW" : "field_110154_aX",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_130068_bOField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bP" : "field_130068_bO",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_130068_bOField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bP" : "field_130068_bO",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_143005_bXField", "()J", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bX" : "field_143005_bX",
                "J");
        mv.visitInsn(173);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_143005_bXField", "(J)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(22, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bX" : "field_143005_bX",
                "J");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_147101_bUField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bU" : "field_147101_bU",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_147101_bUField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bU" : "field_147101_bU",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getField_147103_bOField",
                this.isObfuscated ? "()Lpg;" : "()Lnet/minecraft/stats/StatisticsFile;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bO" : "field_147103_bO",
                this.isObfuscated ? "Lpg;" : "Lnet/minecraft/stats/StatisticsFile;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_70135_KField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "J" : "field_70135_K",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_70135_KField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "J" : "field_70135_K",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_70741_aBField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aZ" : "field_70741_aB",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_70741_aBField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aZ" : "field_70741_aB",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_70763_axField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aY" : "field_70763_ax",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_70763_axField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aY" : "field_70763_ax",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_70764_awField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aX" : "field_70764_aw",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_70764_awField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aX" : "field_70764_aw",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_70768_auField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aV" : "field_70768_au",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_70768_auField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aV" : "field_70768_au",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_70769_aoField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aK" : "field_70769_ao",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_70769_aoField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aK" : "field_70769_ao",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_70770_apField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aL" : "field_70770_ap",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_70770_apField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aL" : "field_70770_ap",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_71079_bUField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bC" : "field_71079_bU",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_71079_bUField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bC" : "field_71079_bU",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_71082_cxField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "cc" : "field_71082_cx",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_71082_cxField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "cc" : "field_71082_cx",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_71085_bRField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bz" : "field_71085_bR",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_71085_bRField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bz" : "field_71085_bR",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_71089_bVField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bD" : "field_71089_bV",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_71089_bVField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bD" : "field_71089_bV",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_71091_bMField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bu" : "field_71091_bM",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_71091_bMField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bu" : "field_71091_bM",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_71094_bPField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bx" : "field_71094_bP",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_71094_bPField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bx" : "field_71094_bP",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_71095_bQField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "by" : "field_71095_bQ",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_71095_bQField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "by" : "field_71095_bQ",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_71096_bNField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bv" : "field_71096_bN",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_71096_bNField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bv" : "field_71096_bN",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getField_71097_bOField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bw" : "field_71097_bO",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setField_71097_bOField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bw" : "field_71097_bO",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getFireResistanceField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ab" : "fireResistance",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setFireResistanceField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ab" : "fireResistance",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getFishEntityField",
                this.isObfuscated ? "()Lxe;" : "()Lnet/minecraft/entity/projectile/EntityFishHook;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bK" : "fishEntity",
                this.isObfuscated ? "Lxe;" : "Lnet/minecraft/entity/projectile/EntityFishHook;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setFishEntityField",
                this.isObfuscated ? "(Lxe;)V" : "(Lnet/minecraft/entity/projectile/EntityFishHook;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bK" : "fishEntity",
                this.isObfuscated ? "Lxe;" : "Lnet/minecraft/entity/projectile/EntityFishHook;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getFlyToggleTimerField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bq" : "flyToggleTimer",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setFlyToggleTimerField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bq" : "flyToggleTimer",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getFoodStatsField",
                this.isObfuscated ? "()Lzr;" : "()Lnet/minecraft/util/FoodStats;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bp" : "foodStats",
                this.isObfuscated ? "Lzr;" : "Lnet/minecraft/util/FoodStats;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setFoodStatsField",
                this.isObfuscated ? "(Lzr;)V" : "(Lnet/minecraft/util/FoodStats;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bp" : "foodStats",
                this.isObfuscated ? "Lzr;" : "Lnet/minecraft/util/FoodStats;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getForceSpawnField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "n" : "forceSpawn",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setForceSpawnField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "n" : "forceSpawn",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getHeightField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "N" : "height",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setHeightField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "N" : "height",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getHurtResistantTimeField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ad" : "hurtResistantTime",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setHurtResistantTimeField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ad" : "hurtResistantTime",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getHurtTimeField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ax" : "hurtTime",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setHurtTimeField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ax" : "hurtTime",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIgnoreFrustumCheckField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ak" : "ignoreFrustumCheck",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIgnoreFrustumCheckField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ak" : "ignoreFrustumCheck",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getInPortalField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "an" : "inPortal",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setInPortalField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "an" : "inPortal",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getInWaterField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ac" : "inWater",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setInWaterField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ac" : "inWater",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getInventoryField",
                this.isObfuscated ? "()Lyx;" : "()Lnet/minecraft/entity/player/InventoryPlayer;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bm" : "inventory",
                this.isObfuscated ? "Lyx;" : "Lnet/minecraft/entity/player/InventoryPlayer;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setInventoryField",
                this.isObfuscated ? "(Lyx;)V" : "(Lnet/minecraft/entity/player/InventoryPlayer;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bm" : "inventory",
                this.isObfuscated ? "Lyx;" : "Lnet/minecraft/entity/player/InventoryPlayer;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getInventoryContainerField",
                this.isObfuscated ? "()Lzs;" : "()Lnet/minecraft/inventory/Container;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bn" : "inventoryContainer",
                this.isObfuscated ? "Lzs;" : "Lnet/minecraft/inventory/Container;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setInventoryContainerField",
                this.isObfuscated ? "(Lzs;)V" : "(Lnet/minecraft/inventory/Container;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bn" : "inventoryContainer",
                this.isObfuscated ? "Lzs;" : "Lnet/minecraft/inventory/Container;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsAirBorneField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "al" : "isAirBorne",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsAirBorneField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "al" : "isAirBorne",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsChangingQuantityOnlyField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "g" : "isChangingQuantityOnly",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsChangingQuantityOnlyField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "g" : "isChangingQuantityOnly",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsCollidedField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "G" : "isCollided",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsCollidedField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "G" : "isCollided",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsCollidedHorizontallyField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "E" : "isCollidedHorizontally",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsCollidedHorizontallyField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "E" : "isCollidedHorizontally",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsCollidedVerticallyField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "F" : "isCollidedVertically",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsCollidedVerticallyField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "F" : "isCollidedVertically",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsDeadField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "K" : "isDead",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsDeadField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "K" : "isDead",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsImmuneToFireField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ae" : "isImmuneToFire",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsImmuneToFireField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ae" : "isImmuneToFire",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsInWebField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "I" : "isInWeb",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsInWebField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "I" : "isInWeb",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsJumpingField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bc" : "isJumping",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsJumpingField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bc" : "isJumping",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getIsSwingInProgressField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "at" : "isSwingInProgress",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setIsSwingInProgressField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "at" : "isSwingInProgress",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getJumpMovementFactorField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aQ" : "jumpMovementFactor",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setJumpMovementFactorField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aQ" : "jumpMovementFactor",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLastDamageField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bb" : "lastDamage",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setLastDamageField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bb" : "lastDamage",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLastExperienceField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bT" : "lastExperience",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setLastExperienceField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bT" : "lastExperience",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLastFoodLevelField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bR" : "lastFoodLevel",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setLastFoodLevelField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bR" : "lastFoodLevel",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLastHealthField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bQ" : "lastHealth",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setLastHealthField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bQ" : "lastHealth",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLastTickPosXField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "S" : "lastTickPosX",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setLastTickPosXField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "S" : "lastTickPosX",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLastTickPosYField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "T" : "lastTickPosY",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setLastTickPosYField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "T" : "lastTickPosY",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLastTickPosZField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "U" : "lastTickPosZ",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setLastTickPosZField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "U" : "lastTickPosZ",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLimbSwingField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aG" : "limbSwing",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setLimbSwingField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aG" : "limbSwing",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLimbSwingAmountField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aF" : "limbSwingAmount",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setLimbSwingAmountField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aF" : "limbSwingAmount",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getLoadedChunksField", "()Ljava/util/List;", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "f" : "loadedChunks",
                "Ljava/util/List;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getLoggerField",
                "()Lorg/apache/logging/log4j/Logger;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bL" : "logger",
                "Lorg/apache/logging/log4j/Logger;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getManagedPosXField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "d" : "managedPosX",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setManagedPosXField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "d" : "managedPosX",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getManagedPosZField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "e" : "managedPosZ",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setManagedPosZField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "e" : "managedPosZ",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getMaxHurtResistantTimeField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aH" : "maxHurtResistantTime",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setMaxHurtResistantTimeField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aH" : "maxHurtResistantTime",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getMaxHurtTimeField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ay" : "maxHurtTime",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setMaxHurtTimeField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ay" : "maxHurtTime",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getMcServerField",
                "()Lnet/minecraft/server/MinecraftServer;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "b" : "mcServer",
                "Lnet/minecraft/server/MinecraftServer;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getMotionXField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "v" : "motionX",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setMotionXField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "v" : "motionX",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getMotionYField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "w" : "motionY",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setMotionYField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "w" : "motionY",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getMotionZField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "x" : "motionZ",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setMotionZField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "x" : "motionZ",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getMoveForwardField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "be" : "moveForward",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setMoveForwardField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "be" : "moveForward",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getMoveStrafingField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bd" : "moveStrafing",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setMoveStrafingField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bd" : "moveStrafing",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getMyEntitySizeField",
                this.isObfuscated ? "()Lse;" : "()Lnet/minecraft/entity/Entity$EnumEntitySize;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "as" : "myEntitySize",
                this.isObfuscated ? "Lse;" : "Lnet/minecraft/entity/Entity$EnumEntitySize;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setMyEntitySizeField",
                this.isObfuscated ? "(Lse;)V" : "(Lnet/minecraft/entity/Entity$EnumEntitySize;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "as" : "myEntitySize",
                this.isObfuscated ? "Lse;" : "Lnet/minecraft/entity/Entity$EnumEntitySize;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getNewPosRotationIncrementsField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bg" : "newPosRotationIncrements",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setNewPosRotationIncrementsField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bg" : "newPosRotationIncrements",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getNewPosXField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bh" : "newPosX",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setNewPosXField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bh" : "newPosX",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getNewPosYField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bi" : "newPosY",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setNewPosYField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bi" : "newPosY",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getNewPosZField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bj" : "newPosZ",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setNewPosZField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bj" : "newPosZ",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getNewRotationPitchField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bl" : "newRotationPitch",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setNewRotationPitchField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bl" : "newRotationPitch",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getNewRotationYawField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bk" : "newRotationYaw",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setNewRotationYawField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bk" : "newRotationYaw",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getNoClipField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "X" : "noClip",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setNoClipField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "X" : "noClip",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getOnGroundField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "D" : "onGround",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setOnGroundField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "D" : "onGround",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getOpenContainerField",
                this.isObfuscated ? "()Lzs;" : "()Lnet/minecraft/inventory/Container;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bo" : "openContainer",
                this.isObfuscated ? "Lzs;" : "Lnet/minecraft/inventory/Container;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setOpenContainerField",
                this.isObfuscated ? "(Lzs;)V" : "(Lnet/minecraft/inventory/Container;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bo" : "openContainer",
                this.isObfuscated ? "Lzs;" : "Lnet/minecraft/inventory/Container;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPingField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "h" : "ping",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPingField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "h" : "ping",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPlayerConqueredTheEndField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "i" : "playerConqueredTheEnd",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPlayerConqueredTheEndField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "i" : "playerConqueredTheEnd",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getPlayerLocationField",
                this.isObfuscated ? "()Lr;" : "()Lnet/minecraft/util/ChunkCoordinates;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bB" : "playerLocation",
                this.isObfuscated ? "Lr;" : "Lnet/minecraft/util/ChunkCoordinates;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setPlayerLocationField",
                this.isObfuscated ? "(Lr;)V" : "(Lnet/minecraft/util/ChunkCoordinates;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bB" : "playerLocation",
                this.isObfuscated ? "Lr;" : "Lnet/minecraft/util/ChunkCoordinates;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getPlayerNetServerHandlerField",
                this.isObfuscated ? "()Lnh;" : "()Lnet/minecraft/network/NetHandlerPlayServer;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "playerNetServerHandler",
                this.isObfuscated ? "Lnh;" : "Lnet/minecraft/network/NetHandlerPlayServer;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setPlayerNetServerHandlerField",
                this.isObfuscated ? "(Lnh;)V" : "(Lnet/minecraft/network/NetHandlerPlayServer;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "a" : "playerNetServerHandler",
                this.isObfuscated ? "Lnh;" : "Lnet/minecraft/network/NetHandlerPlayServer;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPortalCounterField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ao" : "portalCounter",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPortalCounterField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ao" : "portalCounter",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPosXField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "s" : "posX",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPosXField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "s" : "posX",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPosYField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "t" : "posY",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPosYField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "t" : "posY",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPosZField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "u" : "posZ",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPosZField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "u" : "posZ",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevCameraPitchField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aI" : "prevCameraPitch",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevCameraPitchField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aI" : "prevCameraPitch",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevCameraYawField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "br" : "prevCameraYaw",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevCameraYawField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "br" : "prevCameraYaw",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevDistanceWalkedModifiedField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "O" : "prevDistanceWalkedModified",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevDistanceWalkedModifiedField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "O" : "prevDistanceWalkedModified",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevHealthField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aw" : "prevHealth",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevHealthField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aw" : "prevHealth",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevLimbSwingAmountField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aE" : "prevLimbSwingAmount",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevLimbSwingAmountField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aE" : "prevLimbSwingAmount",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevPosXField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "p" : "prevPosX",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevPosXField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "p" : "prevPosX",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevPosYField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "q" : "prevPosY",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevPosYField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "q" : "prevPosY",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevPosZField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "r" : "prevPosZ",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevPosZField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "r" : "prevPosZ",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevRenderYawOffsetField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aN" : "prevRenderYawOffset",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevRenderYawOffsetField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aN" : "prevRenderYawOffset",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevRotationPitchField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "B" : "prevRotationPitch",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevRotationPitchField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "B" : "prevRotationPitch",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevRotationYawField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "A" : "prevRotationYaw",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevRotationYawField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "A" : "prevRotationYaw",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevRotationYawHeadField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aP" : "prevRotationYawHead",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevRotationYawHeadField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aP" : "prevRotationYawHead",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPrevSwingProgressField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aC" : "prevSwingProgress",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPrevSwingProgressField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aC" : "prevSwingProgress",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getPreventEntitySpawningField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "k" : "preventEntitySpawning",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setPreventEntitySpawningField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "k" : "preventEntitySpawning",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRandField", "()Ljava/util/Random;", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "Z" : "rand",
                "Ljava/util/Random;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRandField", "(Ljava/util/Random;)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "Z" : "rand",
                "Ljava/util/Random;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRandomYawVelocityField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bf" : "randomYawVelocity",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRandomYawVelocityField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bf" : "randomYawVelocity",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRecentlyHitField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aS" : "recentlyHit",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRecentlyHitField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aS" : "recentlyHit",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderDistanceWeightField", "()D", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "j" : "renderDistanceWeight",
                "D");
        mv.visitInsn(Opcodes.DRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRenderDistanceWeightField", "(D)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.DLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "j" : "renderDistanceWeight",
                "D");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRenderYawOffsetField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aM" : "renderYawOffset",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRenderYawOffsetField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aM" : "renderYawOffset",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getRiddenByEntityField",
                this.isObfuscated ? "()Lsa;" : "()Lnet/minecraft/entity/Entity;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "l" : "riddenByEntity",
                this.isObfuscated ? "Lsa;" : "Lnet/minecraft/entity/Entity;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setRiddenByEntityField",
                this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "l" : "riddenByEntity",
                this.isObfuscated ? "Lsa;" : "Lnet/minecraft/entity/Entity;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getRidingEntityField",
                this.isObfuscated ? "()Lsa;" : "()Lnet/minecraft/entity/Entity;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "m" : "ridingEntity",
                this.isObfuscated ? "Lsa;" : "Lnet/minecraft/entity/Entity;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setRidingEntityField",
                this.isObfuscated ? "(Lsa;)V" : "(Lnet/minecraft/entity/Entity;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "m" : "ridingEntity",
                this.isObfuscated ? "Lsa;" : "Lnet/minecraft/entity/Entity;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRotationPitchField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "z" : "rotationPitch",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRotationPitchField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "z" : "rotationPitch",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRotationYawField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "y" : "rotationYaw",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRotationYawField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "y" : "rotationYaw",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getRotationYawHeadField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aO" : "rotationYawHead",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setRotationYawHeadField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aO" : "rotationYawHead",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getScoreValueField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ba" : "scoreValue",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setScoreValueField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ba" : "scoreValue",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getServerPosXField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bZ" : "serverPosX",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setServerPosXField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bZ" : "serverPosX",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getServerPosYField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ca" : "serverPosY",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setServerPosYField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "ca" : "serverPosY",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getServerPosZField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "cb" : "serverPosZ",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setServerPosZField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "cb" : "serverPosZ",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSleepingField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bA" : "sleeping",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setSleepingField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bA" : "sleeping",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSpeedInAirField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bJ" : "speedInAir",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setSpeedInAirField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bJ" : "speedInAir",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSpeedOnGroundField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bI" : "speedOnGround",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setSpeedOnGroundField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bI" : "speedOnGround",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getStepHeightField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "W" : "stepHeight",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setStepHeightField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "W" : "stepHeight",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSwingProgressField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aD" : "swingProgress",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setSwingProgressField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aD" : "swingProgress",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getSwingProgressIntField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "au" : "swingProgressInt",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setSwingProgressIntField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "au" : "swingProgressInt",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getTeleportDirectionField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aq" : "teleportDirection",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setTeleportDirectionField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aq" : "teleportDirection",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getTheItemInWorldManagerField",
                this.isObfuscated ? "()Lmx;" : "()Lnet/minecraft/server/management/ItemInWorldManager;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "c" : "theItemInWorldManager",
                this.isObfuscated ? "Lmx;" : "Lnet/minecraft/server/management/ItemInWorldManager;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getTicksExistedField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aa" : "ticksExisted",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setTicksExistedField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "aa" : "ticksExisted",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getTimeUntilPortalField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "am" : "timeUntilPortal",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setTimeUntilPortalField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "am" : "timeUntilPortal",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getTranslatorField", "()Ljava/lang/String;", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bM" : "translator",
                "Ljava/lang/String;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setTranslatorField", "(Ljava/lang/String;)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bM" : "translator",
                "Ljava/lang/String;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getVelocityChangedField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "H" : "velocityChanged",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setVelocityChangedField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "H" : "velocityChanged",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getWasHungryField", "()Z", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bS" : "wasHungry",
                "Z");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setWasHungryField", "(Z)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bS" : "wasHungry",
                "Z");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getWidthField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "M" : "width",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setWidthField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "M" : "width",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getWorldObjField",
                this.isObfuscated ? "()Lahb;" : "()Lnet/minecraft/world/World;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "o" : "worldObj",
                this.isObfuscated ? "Lahb;" : "Lnet/minecraft/world/World;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "setWorldObjField",
                this.isObfuscated ? "(Lahb;)V" : "(Lnet/minecraft/world/World;)V",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "o" : "worldObj",
                this.isObfuscated ? "Lahb;" : "Lnet/minecraft/world/World;");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getXpCooldownField", "()I", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bt" : "xpCooldown",
                "I");
        mv.visitInsn(Opcodes.IRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setXpCooldownField", "(I)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "bt" : "xpCooldown",
                "I");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getYOffsetField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "L" : "yOffset",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setYOffsetField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "L" : "yOffset",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getYSizeField", "()F", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "V" : "ySize",
                "F");
        mv.visitInsn(Opcodes.FRETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "setYSizeField", "(F)V", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.FLOAD, 1);
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                this.isObfuscated ? "V" : "ySize",
                "F");
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getServerPlayerBase",
                "(Ljava/lang/String;)Lapi/player/server/ServerPlayerBase;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "getServerPlayerBase",
                "(Lapi/player/server/IServerPlayerAPI;Ljava/lang/String;)Lapi/player/server/ServerPlayerBase;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL, "getServerPlayerBaseIds", "()Ljava/util/Set;", null, null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "getServerPlayerBaseIds",
                "(Lapi/player/server/IServerPlayerAPI;)Ljava/util/Set;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "dynamic",
                "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitVarInsn(Opcodes.ALOAD, 2);
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "api/player/server/ServerPlayerAPI",
                "dynamic",
                "(Lapi/player/server/IServerPlayerAPI;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getServerPlayerAPI",
                "()Lapi/player/server/ServerPlayerAPI;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                this.isObfuscated ? "mw" : "net/minecraft/entity/player/EntityPlayerMP",
                "serverPlayerAPI",
                "Lapi/player/server/ServerPlayerAPI;");
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        mv = this.cv.visitMethod(
                Opcodes.ACC_PUBLIC | Opcodes.ACC_FINAL,
                "getEntityPlayerMP",
                this.isObfuscated ? "()Lmw;" : "()Lnet/minecraft/entity/player/EntityPlayerMP;",
                null,
                null);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        this.cv.visitField(
                Opcodes.ACC_PRIVATE | Opcodes.ACC_FINAL,
                "serverPlayerAPI",
                "Lapi/player/server/ServerPlayerAPI;",
                null,
                null);
    }
}
